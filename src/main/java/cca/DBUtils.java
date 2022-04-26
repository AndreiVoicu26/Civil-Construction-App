package cca;

import cca.controllers.AdsListController;
import cca.controllers.AnnouncementController;
import cca.controllers.Controller;
import cca.controllers.HomeContractantController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "an26022002vo");
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "an26022002vo");
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "an26022002vo");
            psInsert = connection.prepareStatement("INSERT INTO announcements (username, title, service, description, location, payment) VALUES(?,?,?,?,?,?)");
            psInsert.setString(1, username);
            psInsert.setString(2,ad.getTitle());
            psInsert.setString(3,ad.getService());
            psInsert.setString(4,ad.getDescription());
            psInsert.setString(5,ad.getLocation());
            psInsert.setString(6,ad.getPayment());
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

    public static void takeAnnouncements(ActionEvent event, String username, String role) {
        ArrayList<Announcement> announcementArrayList = new ArrayList<Announcement>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/civil-construction-app", "root", "an26022002vo");
            preparedStatement = connection.prepareStatement("SELECT * FROM announcements WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(username + " has no announcements published!");
                alert.show();
            } else {
                while(resultSet.next()) {
                    String retrievedTitle = resultSet.getString("title");
                    String retrievedService = resultSet.getString("service");
                    String retrievedDescription = resultSet.getString("description");
                    String retrievedLocation = resultSet.getString("location");
                    String retrievedPayment = resultSet.getString("payment");
                    announcementArrayList.add(new Announcement(retrievedTitle, retrievedService, retrievedDescription, retrievedLocation, retrievedPayment));
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
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("ads-list.fxml"));
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
        stage.setTitle("Announcements List");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
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
