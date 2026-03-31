package functional_interfaces;

//Interface
interface ExportService {

 void exportToCSV();
 void exportToPDF();

 // Default method added later
 default void exportToJSON() {
     System.out.println("Exporting data to JSON format");
 }
}

//Existing class (no change needed)
class ReportExporter implements ExportService {

 public void exportToCSV() {
     System.out.println("Exporting data to CSV format");
 }

 public void exportToPDF() {
     System.out.println("Exporting data to PDF format");
 }
}

//Main class
public class DataExportFeature {
 public static void main(String[] args) {

     ExportService exporter = new ReportExporter();

     exporter.exportToCSV();
     exporter.exportToPDF();
     exporter.exportToJSON(); // new feature works
 }
}
