package com.unifaesp.projetocrudfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();

        //Teste de conexão com o banco
        try {
            Connection connect = DriverManager.getConnection("jdbc:sqlite:/home/valdinei/Documentos/banco_unifaesp.db");
            System.out.println("Conexão OK");
            //Login no sistema
            System.out.println("Digite login:");
            String login = new Scanner(System.in).next();
            System.out.println("Digite senha:");
            String senha = new Scanner(System.in).next();

            String validarLogin = "select *from usuarios where login = ? and senha = ?";
            PreparedStatement loginStatement = connect.prepareStatement(validarLogin);
            loginStatement.setString(1, login);
            loginStatement.setString(2, senha);
            ResultSet loginResult = loginStatement.executeQuery();
            if(loginResult.next()){
                System.out.println("Usuário: " + loginResult.getString("login"));
                System.out.println("Senha: " + loginResult.getString("senha"));
            }else{
                System.out.println("Login incorreto!");
            }
            //Executar select
            Statement consulta = connect.createStatement();
            ResultSet resultado = consulta.executeQuery("select *from filmes");
            //Ler resultado da consulta
            while(resultado.next()){
                System.out.println(resultado.getInt("id"));
                System.out.println(resultado.getString("data_lancamento"));
            }
            //Inserção de dados
            String insercao = "insert into filmes (nome, autor, diretor, data_lancamento) values (?, ?, ?, ?)";
            PreparedStatement prepare = connect.prepareStatement(insercao);
            prepare.setString(1, "E o vento levou");
            prepare.setString(2, "Autor autor");
            prepare.setString(3, "Diretor diretor");
            prepare.setString(4, "2021-12-12");
            prepare.execute();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Windows -> "jdbc:sqlite:c:\\Users\\nome\\Documents\\banco.db"
        //Classes utilizadas Connetion, DriverManager, Statement, ResultSet, PreparedStatement
    }

    public static void main(String[] args) {
        launch();
    }
}
