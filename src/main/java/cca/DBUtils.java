package cca;

import cca.controllers.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
