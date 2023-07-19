module com.example.testb210248c.test_b210248c {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.testb210248c.test_b210248c to javafx.fxml;
    exports com.example.testb210248c.test_b210248c;
}