module com.unifaesp.projetocrudfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.unifaesp.projetocrudfx to javafx.fxml;
    exports com.unifaesp.projetocrudfx;
}