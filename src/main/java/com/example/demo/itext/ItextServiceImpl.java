package com.example.demo.itext;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class ItextServiceImpl implements ItextService {
    @Override
    public void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    @Override
    public void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    @Override
    public void addCustomRows(PdfPTable table) throws IOException, BadElementException, URISyntaxException {
        Path path = Paths.get("/Users/ewigkeit/Downloads/Korolev MM-15-2 Perceptron/Results non separated classes (10-20% cross square)/result.png");
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell(img);
        table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }

    @Override
    public void fileEncryption() throws IOException, DocumentException {
        PdfReader pdfReader = new PdfReader("HelloWorld.pdf");
        PdfStamper pdfStamper
                = new PdfStamper(pdfReader, new FileOutputStream("encryptedPdf.pdf"));

        pdfStamper.setEncryption(
                "userpass".getBytes(),
                "userpass".getBytes(),
                0,
                PdfWriter.ENCRYPTION_AES_256
        );

        pdfStamper.close();
    }

    public void hello() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);

        document.add(chunk);
        document.close();
    }

    @Override
    public void addImage() throws IOException, DocumentException {
        Path path = Paths.get("/Users/ewigkeit/maps/1985k.png");

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextImageExample.pdf"));
        document.open();
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        document.add(img);

        document.close();
    }

    @Override
    public void addXml() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("iTextXML.pdf"));
        StringBuilder htmlString = new StringBuilder();
        htmlString.append(new String("<html><body> This is HMTL to PDF conversion Example<table border='2' align='center'> "));
        htmlString.append(new String("<tr><td>JavaCodeGeeks</td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));
        htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='www.google.com'>Google</a> </td> </tr></table></body></html>"));

        document.open();
        InputStream is = new ByteArrayInputStream(htmlString.toString().getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, is);
        document.close();
    }

    @Override
    public void addTable() throws IOException, DocumentException, URISyntaxException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(3);

        addTableHeader(table);
        addRows(table);
        addCustomRows(table);

        document.add(table);
        document.close();

    }
}
