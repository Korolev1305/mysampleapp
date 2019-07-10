package com.example.demo;

import com.example.demo.itext.ItextService;
import com.example.demo.itext.ItextServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class SpringSampleProjectApplication {

    public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {
        SpringApplication.run(SpringSampleProjectApplication.class, args);

        ItextService itextService = new ItextServiceImpl();
        itextService.addXml();

    }

}
