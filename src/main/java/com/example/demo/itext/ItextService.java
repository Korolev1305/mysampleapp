package com.example.demo.itext;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ItextService {

    void addTableHeader(PdfPTable table);

    void addRows(PdfPTable table);

    void addCustomRows(PdfPTable table) throws IOException, BadElementException, URISyntaxException;

    void fileEncryption() throws IOException, DocumentException;

    void addTable() throws IOException, DocumentException, URISyntaxException;

    void addImage() throws IOException, DocumentException;

    void addXml() throws IOException, DocumentException;




}
