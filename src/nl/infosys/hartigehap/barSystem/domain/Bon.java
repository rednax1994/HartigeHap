/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.domain;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author robbie
 */
public class Bon {

    private String file;
    private ImmutableBestelling bestelling;

    public Bon(ImmutableBestelling bestelling) {
        this.bestelling = bestelling;

        file = "././resources/" + bestelling.getId() + ".pdf";

        create();

        //print();
        //delete();
    }

    /**
     * Creates a PDF document.
     *
     * @param filename the path to the new PDF document
     * @throws DocumentException
     * @throws IOException
     */
    private void create() {
        try {

            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            document.add(header());

            document.add(Chunk.NEWLINE);

            document.add(table());

            document.setMargins(0, 0, 0, 100);

            document.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Paragraph header() {
        Paragraph par = new Paragraph("Eetcafe de hartige hap", FontFactory.getFont("Times-Roman", 20, Font.BOLD));

        par.setAlignment(Element.ALIGN_CENTER);

        return par;
    }

    private Paragraph table() {

        Paragraph par = new Paragraph();

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        PdfPCell cell;

        Font fontbold = FontFactory.getFont("Times-Roman", 13, Font.BOLD);
        Font normal = FontFactory.getFont("Times-Roman", 13);

        table.addCell(new Phrase("Product:", fontbold));
        table.addCell(new Phrase("Prijs:", fontbold));
        table.addCell(new Phrase("Aantal:", fontbold));
        table.addCell(new Phrase("Totaalprijs:", fontbold));

        for (ImmutableProduct product : bestelling.getProducten()) {
            table.addCell(new Phrase(product.getNaam(), normal));
            table.addCell(new Phrase(product.getPrijsFormat(), normal));
            table.addCell(new Phrase(String.valueOf(product.getAantal()), normal));

            cell = new PdfPCell(new Phrase(product.getTotaalPrijsFormat(), normal));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            cell.setPaddingBottom(5);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Excl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(bestelling.getPrijsExclBtwFormat(), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("BTW 21%", fontbold));
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(bestelling.getBtwBedragFormat(), fontbold));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Incl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(bestelling.getTotaalPrijsFormat(), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);
        par.add(table);

        return par;
    }

    private void print() throws FileNotFoundException, PrintException, IOException {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));

            // create a PDF doc flavor
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;

            // Locate the default print service for this environment.
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            System.out.println(service.getName());

        // Create and return a PrintJob capable of handling data from
            // any of the supported document flavors.
            DocPrintJob printJob = service.createPrintJob();

        // Construct a SimpleDoc with the specified 
            // print data, doc flavor and doc attribute set.
            Doc doc = new SimpleDoc(is, flavor, null);

            // Print a document with the specified job attributes.
            printJob.print(doc, null);

            is.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void delete() {
        File deleteFile = new File(file);

        deleteFile.delete();
    }
}
