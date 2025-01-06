package com.example.bagistakipsistemi.Controllers;

import com.example.bagistakipsistemi.Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class IndividualUserAccountSceneController implements Initializable {
    @FXML
    private Label myTitleLabel;

    @FXML
    private TextField myNameTextField, mySurnameTextField, myTelnoTextField,
            myNicknameTextField ,myEmailTextField, myPasswordTextField;

    @FXML
    private Button myEditButton, myDeleteAccountButton ,myBackButton;

    private ArrayList<TextField> textfields = new ArrayList<>();
    private DatabaseManage d1 = new DatabaseManage();
    private Scene scene;
    private Stage stage;
    private Parent root;
    private CurrentUser currentuser;

    // Kullanıcının giriş yapmış olduğu hesabın bilgilerini veritabanından çeker ve onları textfield'lara aktarır
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas){
                if(data instanceof CurrentUser){
                    currentuser = new CurrentUser(((CurrentUser) data).getDatavar1(), ((CurrentUser) data).getDatavar2(),
                            ((CurrentUser) data).getDatavar3(), ((CurrentUser) data).getNickname(),
                            ((CurrentUser) data).getEmail(), ((CurrentUser) data).getPassword(),
                            ((CurrentUser) data).getUserDataType());
                    break;
                }
            }
            myNameTextField.setText(currentuser.getDatavar1());
            mySurnameTextField.setText(currentuser.getDatavar2());
            myTelnoTextField.setText(currentuser.getDatavar3());
            myNicknameTextField.setText(currentuser.getNickname());
            myEmailTextField.setText(currentuser.getEmail());
            myPasswordTextField.setText(currentuser.getPassword());
            textfields.add(myNameTextField);
            textfields.add(mySurnameTextField);
            textfields.add(myTelnoTextField);
            textfields.add(myNicknameTextField);
            textfields.add(myEmailTextField);
            textfields.add(myPasswordTextField);
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kullanıcının girmiş olduğu bilgileri doğrulayıp ona göre hesabını günceller ve bunu veritabanına kaydeder
    public void editaccount(ActionEvent event) {
        boolean isBlank = false;
        boolean isexist = false;
        try{
            for (TextField field : textfields) {
                if(field.getText().isEmpty()){
                    isBlank = true;
                    break;
                }
            }
            if(!isBlank){
                ArrayList<Data> datas = d1.Read_data();
                for(Data data : datas){
                    if(data instanceof IndividualUser){
                        if(myNicknameTextField.getText().equals(((IndividualUser) data).getNickname())
                            || myEmailTextField.getText().equals(((IndividualUser) data).getEmail())
                            || Integer.parseInt(myTelnoTextField.getText()) == ((IndividualUser) data).getTelephonenumber()){
                            isexist = true;
                        }
                    }
                    else if(data instanceof InstutionalUser){
                        if(myNicknameTextField.getText().equals(((InstutionalUser) data).getNickname())
                            || myEmailTextField.getText().equals(((InstutionalUser) data).getEmail())){
                            isexist = true;
                        }
                    }
                    else if(data instanceof AdminUser){
                        if(myNicknameTextField.getText().equals(((AdminUser) data).getNickname())){
                            isexist = true;
                        }
                    }
                }
                d1.Clear_data();
                if(isexist){
                    for(Data data : datas) {
                        d1.Save_to_data(data);
                    }
                    throw new IllegalArgumentException();
                }
                else{
                    for(Data data : datas) {
                        if (data instanceof IndividualUser) {
                            if (currentuser.getEmail().equals(((IndividualUser) data).getEmail())) {
                                ((IndividualUser) data).setName(myNameTextField.getText());
                                ((IndividualUser) data).setSurname(mySurnameTextField.getText());
                                ((IndividualUser) data).setTelephonenumber(Integer.parseInt(myTelnoTextField.getText()));
                                ((IndividualUser) data).setNickname(myNicknameTextField.getText());
                                ((IndividualUser) data).setEmail(myEmailTextField.getText());
                                ((IndividualUser) data).setPassword(myPasswordTextField.getText());
                            }
                        }
                        else if(data instanceof CurrentUser){
                            if(currentuser.getEmail().equals(((CurrentUser) data).getEmail())){
                                ((CurrentUser) data).setDatavar1(myNameTextField.getText());
                                ((CurrentUser) data).setDatavar2(mySurnameTextField.getText());
                                ((CurrentUser) data).setDatavar3(myTelnoTextField.getText());
                                ((CurrentUser) data).setNickname(myNicknameTextField.getText());
                                ((CurrentUser) data).setEmail(myEmailTextField.getText());
                                ((CurrentUser) data).setPassword(myPasswordTextField.getText());
                            }
                        }
                        d1.Save_to_data(data);
                    }
                    showAlert("Başarılı", "Başarılı bir şekilde bilgilerinizi değiştirdiniz", Alert.AlertType.CONFIRMATION);
                    switchtomainscene2(event);
                }
            }
            else
                throw new NullPointerException();
        }
        catch(NumberFormatException e){
            showAlert("Hata", "Telefon alanina sayi giriniz!", Alert.AlertType.ERROR);
        }
        catch(NullPointerException e){
            showAlert("Hata", "Alanlari bos birakmayiniz!", Alert.AlertType.ERROR);
        }
        catch(IllegalArgumentException e){
            showAlert("Hata", "Bu bilgilere sahip bir hesap var!", Alert.AlertType.ERROR);
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Kullanıcının giriş yapmış olduğu hesabı siler ve bunu veritabanında da uygular
    public void deleteaccount(ActionEvent event) {
        try{
            ArrayList<Data> datas = d1.Read_data();
            for(Data data : datas) {
                if (data instanceof IndividualUser) {
                    if(currentuser.getEmail().equals(((IndividualUser) data).getEmail())){
                        d1.Delete_data(data, datas);
                        datas = d1.Read_data();
                        d1.Delete_data(currentuser, datas);
                        break;
                    }
                }
            }
            showAlert("Başarılı", "Başarılı bir şekilde hesabınızı sildiniz", Alert.AlertType.CONFIRMATION);
            switchtomainscene1(event);
        }
        catch(Exception e){
            showAlert("Hata", "Error!", Alert.AlertType.ERROR);
        }
    }

    // Ana Sayfa 1'e gönderir
    public void switchtomainscene1(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene1.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Ana Sayfa 2'e gönderir
    public void switchtomainscene2(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/bagistakipsistemi/MainScene2.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // MessageBox penceresi oluşturur ve girilen parametrelere göre onu şekillendirir
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
