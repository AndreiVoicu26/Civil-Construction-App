package cca;

import cca.controllers.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String role) {
        Parent root = null;

        if(username != null && role != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                Controller homeController = loader.getController();
                homeController.setUserInformation(username, role);
                homeController.saveUserInformation(username, role);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene2(Event event, String fxmlFile, String title, String username, String role, Announcement ad) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdDetailsController adController = loader.getController();
            adController.displayAnnouncement(ad);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene3(ActionEvent event, String fxmlFile, String title, String username, String role, Announcement ad) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdEditController adController = loader.getController();
            adController.getAnnouncement(ad);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene4(ActionEvent event, String fxmlFile, String title, String username, String role, Announcement ad, String fxmlSource) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdPromoteController promoteController = loader.getController();
            promoteController.getAnnouncement(ad, fxmlSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene5(ActionEvent event, String fxmlFile, String title, String username, String role, Announcement ad) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AnnouncementController promoteController = loader.getController();
            promoteController.getAnnouncement(ad);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene6(ActionEvent event, String fxmlFile, String title, String username, String role, Announcement ad) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdEditController promoteController = loader.getController();
            promoteController.getAnnouncement(ad);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void changeScene9(MouseEvent event, String fxmlFile, String title, String username, String role, Announcement ad, User user) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdDetailsClientController adController = loader.getController();
            adController.displayAnnouncement(ad);
            adController.getUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene7(ActionEvent event, String fxmlFile, String title, String username, String role, User user) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            UserEditController userEditController = loader.getController();
            userEditController.getUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changeScene8(ActionEvent event, String fxmlFile, String title, String username, String role, User user) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AccountInfoController accountInfoController = loader.getController();
            accountInfoController.getUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void changePassword(ActionEvent event, String username, String role, String oldPassword, String newPassword) {
        Connection connection = null;
        PreparedStatement psCheckPassword = null;
        PreparedStatement psUpdatePassword = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psCheckPassword = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckPassword.setString(1, username);
            resultSet = psCheckPassword.executeQuery();

            while(resultSet.next()) {
                String retrievedPassword = resultSet.getString("password");
                if(!toHexString(getSHA(oldPassword)).equals(retrievedPassword)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password incorrect!");
                    alert.show();
                } else {
                    if(newPassword.length() < 6) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password must have minimum 6 characters");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Are you sure you want to change password?");
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.OK) {
                            psUpdatePassword = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
                            psUpdatePassword.setString(1, toHexString(getSHA(newPassword)));
                            psUpdatePassword.setString(2, username);
                            psUpdatePassword.executeUpdate();

                            Parent root = null;

                            try {
                                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("log-in.fxml"));
                                root = loader.load();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setTitle("Log In");
                            stage.setScene(new Scene(root, 600, 400));
                            stage.show();
                        } else {
                            alert.close();
                        }
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckPassword != null) {
                try {
                    psCheckPassword.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psUpdatePassword != null) {
                try {
                    psUpdatePassword.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void addCard(ActionEvent event, String fxmlFile, String title, String username, String role, Card card, Announcement ad) {
        Connection connection = null;
        PreparedStatement psInsert = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psInsert = connection.prepareStatement("UPDATE users SET cardnumber = ?, expdate = ?, cvc = ? WHERE username = ? ");
            psInsert.setString(1,toHexString(getSHA(card.getCardNumber())));
            psInsert.setString(2,toHexString(getSHA(card.getExpirationDate())));
            psInsert.setString(3,toHexString(getSHA(card.getCVC())));
            psInsert.setString(4,username);
            psInsert.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AnnouncementController promoteController = loader.getController();
            promoteController.getPromotion();
            promoteController.getAnnouncement(ad);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void addCard2(ActionEvent event, String fxmlFile, String title, String username, String role, Card card, Announcement ad) {
        Connection connection = null;
        PreparedStatement psInsert = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psInsert = connection.prepareStatement("UPDATE users SET cardnumber = ?, expdate = ?, cvc = ? WHERE username = ? ");
            psInsert.setString(1,toHexString(getSHA(card.getCardNumber())));
            psInsert.setString(2,toHexString(getSHA(card.getExpirationDate())));
            psInsert.setString(3,toHexString(getSHA(card.getCVC())));
            psInsert.setString(4,username);
            psInsert.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdEditController promoteController = loader.getController();
            promoteController.getPromotion();
            promoteController.getAnnouncement(ad);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void takeInfo(ActionEvent event, String fxmlFile, String title, String username, String role) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                String retrievedName = resultSet.getString("fullname");
                String retrievedEmail = resultSet.getString("email");
                String retrievedPhone = resultSet.getString("phone");
                String retrievedAddress = resultSet.getString("address");
                user = new User(retrievedName, retrievedEmail, retrievedPhone, retrievedAddress);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AccountInfoController infoController = loader.getController();
            infoController.getUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String role, String fullName, String email, String phone, String address) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            if(password.length() < 6) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Password must have minimum 6 characters");
                alert.show();
            } else {
                if (!username.matches("[a-zA-Z0-9]+")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Username must be alphanumeric!");
                    alert.show();
                } else {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
                    psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                    psCheckUserExists.setString(1, username);
                    resultSet = psCheckUserExists.executeQuery();

                    if (resultSet.isBeforeFirst()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username already taken!");
                        alert.show();
                    } else {
                        psInsert = connection.prepareStatement("INSERT INTO users (username, password, fullname, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)");
                        psInsert.setString(1, username);
                        psInsert.setString(2, toHexString(getSHA(password)));
                        psInsert.setString(3, fullName);
                        psInsert.setString(4, email);
                        psInsert.setString(5, phone);
                        psInsert.setString(6, address);
                        psInsert.setString(7, role);
                        psInsert.executeUpdate();

                        if(role.equals("Contractant")) {
                            changeScene(event, "home-contractant.fxml", "Home", username, role);
                        }else {
                            DBUtils.takeContractants(event, "contractants-list.fxml", "Contractants List", username, role);
                        }
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            preparedStatement = connection.prepareStatement("SELECT password, role FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while(resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedRole = resultSet.getString("role");
                    if(retrievedPassword.equals(toHexString(getSHA(password)))) {
                        if(retrievedRole.equals("Contractant")) {
                            changeScene(event, "home-contractant.fxml", "Home", username, retrievedRole);
                        } else {
                            DBUtils.takeContractants(event, "contractants-list.fxml", "Contractants List", username, retrievedRole);
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addAnnouncement(ActionEvent event, String username, String role, Announcement ad) {
        Connection connection = null;
        PreparedStatement psInsert = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psInsert = connection.prepareStatement("INSERT INTO announcements (username, title, service, description, location, payment, promoted) VALUES(?,?,?,?,?,?,?)");
            psInsert.setString(1, username);
            psInsert.setString(2,ad.getTitle());
            psInsert.setString(3,ad.getService());
            psInsert.setString(4,ad.getDescription());
            psInsert.setString(5,ad.getLocation());
            psInsert.setString(6,ad.getPayment());
            psInsert.setInt(7, ad.getPromoted());
            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("home-contractant.fxml"));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void updateUser(ActionEvent event, String username, String role, User user) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psUpdate = connection.prepareStatement("UPDATE users SET fullname = ?, email = ?, phone = ?, address = ? WHERE username = ?");
            psUpdate.setString(1,user.getName());
            psUpdate.setString(2,user.getEmail());
            psUpdate.setString(3,user.getPhone());
            psUpdate.setString(4,user.getAddress());
            psUpdate.setString(5,username);
            psUpdate.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        DBUtils.takeInfo(event, "account-info.fxml", "Info", username, role);
    }

    public static void updateAnnouncement(ActionEvent event, String username, String role, Announcement ad) {
        Connection connection = null;
        PreparedStatement psUpdate = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psUpdate = connection.prepareStatement("UPDATE announcements SET username = ?, title = ?, service = ?, description = ?, location = ?, payment = ?, promoted = ? WHERE announcement_id = ?");
            psUpdate.setString(1, username);
            psUpdate.setString(2,ad.getTitle());
            psUpdate.setString(3,ad.getService());
            psUpdate.setString(4,ad.getDescription());
            psUpdate.setString(5,ad.getLocation());
            psUpdate.setString(6,ad.getPayment());
            psUpdate.setInt(7, ad.getPromoted());
            psUpdate.setInt(8,ad.getID());
            psUpdate.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        DBUtils.takeAnnouncements(event,"ads-list.fxml","Announcements List", username, role);
    }

    public static void deleteAnnouncement(ActionEvent event, String username, String role, Announcement ad) {
        Connection connection = null;
        PreparedStatement psDelete = null;
        PreparedStatement psCheckNoAnnouncements = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            psDelete = connection.prepareStatement("DELETE FROM announcements WHERE announcement_id = ?");
            psDelete.setInt(1,ad.getID());
            psDelete.executeUpdate();

            psCheckNoAnnouncements = connection.prepareStatement("SELECT * FROM announcements WHERE username = ?");
            psCheckNoAnnouncements.setString(1, username);
            resultSet = psCheckNoAnnouncements.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                DBUtils.changeScene(event,"home-contractant.fxml","Home", username, role);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(username + " has no announcements published!");
                alert.show();
            } else {
                DBUtils.takeAnnouncements(event,"ads-list.fxml","Announcements List", username, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckNoAnnouncements != null) {
                try {
                    psCheckNoAnnouncements.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void takeAnnouncements(ActionEvent event, String fxmlFile, String title, String username, String role) {
        ArrayList<Announcement> announcementArrayList = new ArrayList<Announcement>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            preparedStatement = connection.prepareStatement("SELECT * FROM announcements WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(username + " has no announcements published!");
                alert.show();
                return;
            } else {
                while(resultSet.next()) {
                    String retrievedTitle = resultSet.getString("title");
                    String retrievedService = resultSet.getString("service");
                    String retrievedDescription = resultSet.getString("description");
                    String retrievedLocation = resultSet.getString("location");
                    String retrievedPayment = resultSet.getString("payment");
                    int retrievedID = resultSet.getInt("announcement_id");
                    int retrievedPromoted = resultSet.getInt("promoted");
                    announcementArrayList.add(new Announcement(retrievedTitle, retrievedService, retrievedDescription, retrievedLocation, retrievedPayment, retrievedID, retrievedPromoted));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            AdsListController listController = loader.getController();
            listController.loadData(announcementArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void takeContractantsAds(Event event, String fxmlFile, String title, String username, String role, User user) {
        ArrayList<Announcement> announcementArrayList = new ArrayList<Announcement>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            preparedStatement = connection.prepareStatement("SELECT * FROM announcements WHERE username = ?");
            preparedStatement.setString(1,user.getUsername());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(user.getName() + " has no announcements published!");
                alert.show();
                return;
            } else {
                while(resultSet.next()) {
                    String retrievedTitle = resultSet.getString("title");
                    String retrievedService = resultSet.getString("service");
                    String retrievedDescription = resultSet.getString("description");
                    String retrievedLocation = resultSet.getString("location");
                    String retrievedPayment = resultSet.getString("payment");
                    int retrievedID = resultSet.getInt("announcement_id");
                    int retrievedPromoted = resultSet.getInt("promoted");
                    announcementArrayList.add(new Announcement(retrievedTitle, retrievedService, retrievedDescription, retrievedLocation, retrievedPayment, retrievedID, retrievedPromoted));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            ContractantAdsController listController = loader.getController();
            listController.loadData(announcementArrayList);
            listController.getUser(user);
            listController.setLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void takeContractants(ActionEvent event, String fxmlFile, String title, String username, String role) {
        ArrayList<User> contractantsArrayList = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE role = ?");
            preparedStatement.setString(1, "Contractant");
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No contractants found");
                alert.show();
                return;
            } else {
                while(resultSet.next()) {
                    String retrievedName = resultSet.getString("fullname");
                    String retrievedEmail = resultSet.getString("email");
                    String retrievedPhone = resultSet.getString("phone");
                    String retrievedAddress = resultSet.getString("address");
                    String retrievedUsername = resultSet.getString("username");
                    //int retrievedID = resultSet.getInt("announcement_id");
                    contractantsArrayList.add(new User(retrievedName, retrievedEmail, retrievedPhone, retrievedAddress, retrievedUsername));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Controller homeController = loader.getController();
            homeController.setUserInformation(username, role);
            homeController.saveUserInformation(username, role);
            ContractantsListController listController = loader.getController();
            listController.loadData(contractantsArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public static ArrayList<User> takeContractantsWithSpecificService(String Service) {
        ArrayList<User> contractantsArrayList = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "toor");
            preparedStatement = connection.prepareStatement("SELECT * FROM announcements WHERE service = ?");
            preparedStatement.setString(1, Service);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                preparedStatement2 = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                preparedStatement2.setString(1, retrievedUsername);
                resultSet2 = preparedStatement2.executeQuery();

                while(resultSet2.next()) {
                    String retrievedName = resultSet2.getString("fullname");
                    String retrievedEmail = resultSet2.getString("email");
                    String retrievedPhone = resultSet2.getString("phone");
                    String retrievedAddress = resultSet2.getString("address");
                    contractantsArrayList.add(new User(retrievedName, retrievedEmail, retrievedPhone, retrievedAddress, retrievedUsername));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return contractantsArrayList;
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        /* MessageDigest instance for hashing using SHA512*/
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }



}
