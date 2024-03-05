package tn.esprit.maytest.Utils;

import tn.esprit.maytest.Models.Formation;
import tn.esprit.maytest.Services.ServiceFormation;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author oumayma
 */
public class Pdf {
        public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceFormation m=new ServiceFormation();
        List<Formation> list=m.getAllFormations();    
        document.add(new Paragraph("La liste des Report :"));
        document.add(new Paragraph("     "));
         for(Formation u:list)
        {
        document.add(new Paragraph("Le Titre :"+u.getTitre()));
        document.add(new Paragraph("Le Description de cette tableau artestique est :"+u.getDescription()));
        document.add(new Paragraph("Media :"+u.getMedia()));
        document.add(new Paragraph("nombre de place disponible :"+u.getNb_places()));
        document.add(new Paragraph("facture de Formation :"+u.getPrix()+" DT "));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
         
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
