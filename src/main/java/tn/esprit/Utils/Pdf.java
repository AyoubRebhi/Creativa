package tn.esprit.Utils;

import tn.esprit.Models.Formation;
import tn.esprit.Services.ServiceFormation;
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
        document.add(new Paragraph("La liste des inscriptions  :"));
        document.add(new Paragraph("     "));
         for(Formation u:list)
        {
        document.add(new Paragraph("Titre de la formation  :"+u.getTitre()));
        document.add(new Paragraph("Le Description de la formation :"+u.getDescription()));
        document.add(new Paragraph("Media :"+u.getMedia()));
        document.add(new Paragraph("nombre de places disponibles :"+u.getNb_places()));
        document.add(new Paragraph("prix de la  Formation :"+u.getPrix()+" DT "));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
         
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
