package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import pdf.event.HeaderAndFooterEvent;
import pdf.event.TOCEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.List;

public class TOCEventPDF {

    public static void main(String[] args) {
        String SRC = "\\GitHub\\tutorials\\pdf\\TEMP.pdf";
        String DEST = "\\GitHub\\tutorials\\pdf\\TOC_final.pdf";
        try {
            createPdf(SRC);
            reorderDocument(SRC, DEST);
            Files.deleteIfExists(Paths.get(SRC));
        } catch (IOException | DocumentException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPdf(String dest) throws IOException, DocumentException, URISyntaxException {

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLUE);
        Document document = new Document();
        OutputStream os = Files.newOutputStream(Paths.get(dest));
        PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
        Rectangle rectangle = new Rectangle(30, 30, 550, 800);
        pdfWriter.setBoxSize("rectangle", rectangle);

        HeaderAndFooterEvent headerAndFooterEvent = new HeaderAndFooterEvent();
        pdfWriter.setPageEvent(headerAndFooterEvent);

        TOCEvent tocEvent = new TOCEvent();
        pdfWriter.setPageEvent(tocEvent);
        document.open();
        document.newPage();

        Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.setAbsolutePosition(450f, 675f);
        img.scaleAbsolute(100, 100);
        document.add(img);

        document.newPage();
        addEmptyLine(document, 20);
        Paragraph end = new Paragraph("END OF DOCUMENT",
                new Font(Font.FontFamily.HELVETICA, 40, Font.BOLD, BaseColor.GRAY));
        end.setAlignment(Element.ALIGN_CENTER);
        document.add(end);

        for (int i = 0; i < 10; i++) {
            document.newPage();
            addEmptyLine(document, 2);
            String title = "Chapter - " + i;
            Chunk c = new Chunk(title, titleFont);
            c.setGenericTag(title);
            document.add(new Paragraph(c));
            for (int j = 0; j < 30; j++) {
                document.add(new Paragraph("Line " + j + " of title " + i));
            }
        }
        document.newPage();
        headerAndFooterEvent.setLastPageNumber(pdfWriter.getPageNumber());
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setBorderColor(BaseColor.WHITE);
        addEmptyLine(document, 2);
        Paragraph toc = new Paragraph("Table Of Contents", titleFont);
        toc.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(toc);
        Chunk dottedLine = new Chunk(new DottedLineSeparator());
        List<AbstractMap.SimpleEntry<String, Integer>> entries = tocEvent.getTOC();
        Paragraph pp = new Paragraph("\n");
        for (AbstractMap.SimpleEntry<String, Integer> entry : entries) {
            Chunk c = new Chunk(entry.getKey(), titleFont);
            PdfAction action = PdfAction.gotoLocalPage(entry.getValue(), new PdfDestination(0), pdfWriter);
            c.setAction(action);
            Paragraph p = new Paragraph(c);
            p.add(dottedLine);
            Chunk c1 = new Chunk(String.valueOf(entry.getValue()), titleFont);
            PdfAction action1 = PdfAction.gotoLocalPage(entry.getValue(), new PdfDestination(0), pdfWriter);
            c1.setAction(action1);
            p.add(c1);
            pp.add(p);
            pp.add(new Paragraph("\n"));
        }
        cell.addElement(pp);
        table.addCell(cell);
        table.setTotalWidth(300);
        document.add(table);
        document.close();
    }

    public static void reorderDocument(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        int totalPages = reader.getNumberOfPages();

        // Create a document object for the new reordered document
        Document reorderedDocument = new Document();
        PdfCopy copy = new PdfCopy(reorderedDocument, new FileOutputStream(dest));
        reorderedDocument.open();

        // Add the first page (usually the cover) if exists
        if (totalPages >= 1) {
            PdfImportedPage firstPage = copy.getImportedPage(reader, 1);
            copy.addPage(firstPage);
        }

        // Add the last page to second
        PdfImportedPage lastPage = copy.getImportedPage(reader, totalPages);
        copy.addPage(lastPage);

        // Add pages from the third to the second-last page
        for (int i = 3; i < totalPages; i++) {
            PdfImportedPage page = copy.getImportedPage(reader, i);
            copy.addPage(page);
        }
        if (totalPages >= 2) {
            PdfImportedPage secondPage = copy.getImportedPage(reader, 2);
            copy.addPage(secondPage);
        }
        // Close the reordered document
        reorderedDocument.close();
        reader.close();
    }

    public static void addHeaderFooter(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        int n = reader.getNumberOfPages();
        Document document = new Document(reader.getPageSizeWithRotation(1));

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        HeaderAndFooterEvent headerAndFooterEvent = new HeaderAndFooterEvent();
        writer.setPageEvent(headerAndFooterEvent);

        document.open();
        PdfContentByte cb = writer.getDirectContent();

        PdfImportedPage page;
        for (int i = 1; i <= n; i++) {
            document.newPage();
            page = writer.getImportedPage(reader, i);
            cb.addTemplate(page, 0, 0);
        }

        document.close();
        reader.close();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addEmptyLine(Document document, int number) {
        for (int i = 0; i < number; i++) {
            try {
                document.add(new Paragraph(" "));
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
