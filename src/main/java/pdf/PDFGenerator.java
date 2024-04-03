package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import pdf.event.HeaderAndFooterEvent;
import pdf.event.TOCEvent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.List;

public class PDFGenerator {
    final static String DEST = "C:\\Users\\Ashwanikumar_Singh\\OneDrive - EPAM\\work\\epam\\engx_assignment\\clean-code-java-general-principles_991870bf-dda1-4c37-8590-67a794ab7da4\\Comments\\TOC_final.pdf";

    public static void main(String[] args) {
        try {
            createPdf();
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPdf() throws IOException, DocumentException, URISyntaxException, ClassNotFoundException {
        OutputStream outputStream = Files.newOutputStream(Paths.get(DEST));
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLUE);
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
        Rectangle rectangle = new Rectangle(30, 30, 550, 800);
        pdfWriter.setBoxSize("rectangle", rectangle);

        HeaderAndFooterEvent headerAndFooterEvent = new HeaderAndFooterEvent();
        pdfWriter.setPageEvent(headerAndFooterEvent);

        TOCEvent tocEvent = new TOCEvent();
        pdfWriter.setPageEvent(tocEvent);
        document.open();
        document.newPage();

        document.add(new Paragraph("TITLE OF DOCUMENT",
                new Font(Font.FontFamily.HELVETICA, 40, Font.BOLD, BaseColor.BLACK)));

        document.newPage();
        addEmptyLine(document, 5);
        Paragraph end = new Paragraph("END OF DOCUMENT",
                new Font(Font.FontFamily.HELVETICA, 40, Font.BOLD, BaseColor.GRAY));
        end.setAlignment(Element.ALIGN_CENTER);
        document.add(end);
        document.newPage();

        for (int i = 0; i < 5; i++) {
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

        addPDFDocument(document, pdfWriter);

        headerAndFooterEvent.setLastPageNumber(pdfWriter.getPageNumber());
        addTOC(titleFont, document, pdfWriter, tocEvent);
        document.close();
        reorderDocument(byteArrayOutputStream, outputStream);
    }

    private static void addTOC(Font titleFont, Document document, PdfWriter pdfWriter, TOCEvent tocEvent) throws DocumentException {
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
    }

    public static void addPDFDocument(Document document, PdfWriter writer) throws IOException, DocumentException {
        Path pdfPath = Paths.get("C:\\Users\\Ashwanikumar_Singh\\Downloads\\test_word_file.pdf");
        byte[] pdfByteArray = Files.readAllBytes(pdfPath);
        PdfReader reader = null;
        try {
            reader = new PdfReader(new ByteArrayInputStream(pdfByteArray));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Loop through each page of the PDF byte array and add it to the output PDF
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(reader, i);
            // Create a PdfTemplate for the page
            PdfTemplate template = writer.getDirectContent().createTemplate(reader.getPageSize(i).getWidth(), reader.getPageSize(i).getHeight());

            // Exclude header and footer region by clipping
            template.rectangle(0, 60, page.getWidth(), page.getHeight() - 135);  // Adjust coordinates as necessary
            template.clip();
            template.newPath();
            template.addTemplate(page, 0, 0);

            document.setPageSize(new Rectangle(page.getWidth(), page.getHeight()));
            PdfContentByte contentByte = writer.getDirectContent();
            contentByte.addTemplate(template, 0, 0);

        }
        document.newPage();
        document.setPageSize(PageSize.A4);
    }

    public static void reorderDocument(ByteArrayOutputStream byteArrayOutputStream, OutputStream outputStream)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(byteArrayOutputStream.toByteArray());
        int totalPages = reader.getNumberOfPages();

        // Create a document object for the new reordered document
        Document reorderedDocument = new Document();

        PdfCopy copy = new PdfCopy(reorderedDocument, outputStream);
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
