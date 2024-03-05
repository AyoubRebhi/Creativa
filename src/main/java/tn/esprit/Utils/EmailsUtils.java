package tn.esprit.Utils;

import tn.esprit.Models.User;
import tn.esprit.Services.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.Timestamp;
import java.util.Properties;

public class EmailsUtils {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "hhajer09@gmail.com";
    private static final String PASSWORD = "vhxo yiyf ajcb ktjm";

    public static void sendVerificationEmail(String toEmail, String verificationCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Vérification de l'e-mail");
            message.setText("");
            Multipart multipart = new MimeMultipart();

            // Part 1: Text content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(verificationCode);

            // Part 2: HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"fr\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\"><head><meta charset=\"UTF-8\"><meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"><meta name=\"x-apple-disable-message-reformatting\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta content=\"telephone=no\" name=\"format-detection\"><title>Nouveau message</title> <!--[if (mso 16)]><style type=\"text/css\">     a {text-decoration: none;}     </style><![endif]--> <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> <!--[if gte mso 9]><xml> <o:OfficeDocumentSettings> <o:AllowPNG></o:AllowPNG> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml>\n" +
                    "<![endif]--> <!--[if !mso]><!-- --><link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"> <!--<![endif]--><style type=\"text/css\">#outlook a { padding:0;}.es-button { mso-style-priority:100!important; text-decoration:none!important;}a[x-apple-data-detectors] { color:inherit!important; text-decoration:none!important; font-size:inherit!important; font-family:inherit!important; font-weight:inherit!important; line-height:inherit!important;}.es-desk-hidden { display:none; float:left; overflow:hidden; width:0; max-height:0; line-height:0; mso-hide:all;}@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:36px!important; text-align:left } h2 { font-size:28px!important; text-align:left } h3 { font-size:20px!important; text-align:left }\n" +
                    " .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:28px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important }\n" +
                    " .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:18px!important; display:block!important; border-right-width:0px!important; border-left-width:0px!important; border-top-width:15px!important; border-bottom-width:15px!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important }\n" +
                    " .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important }\n" +
                    " .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important }\n" +
                    " .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }@media screen and (max-width:384px) {.mail-message-content { width:414px!important } }</style>\n" +
                    " </head>\n" +
                    " \n" +
                    " <body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"><div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"fr\" style=\"background-color:#F9F4FF\"> <!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" src=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" color=\"#F9F4FF\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill> </v:background><![endif]--><table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" background=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-image:url(https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png);background-repeat:repeat;background-position:center top;background-color:#F9F4FF\" role=\"none\"><tr>\n" +
                    "<td valign=\"top\" style=\"padding:0;Margin:0\"><table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"><tr><td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"><tr><td align=\"left\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:40px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"left\" style=\"padding:0;Margin:0;width:560px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img src=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/group.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"40\" height=\"40\"></a> </td></tr></table></td></tr></table></td></tr></table></td></tr></table>\n" +
                    " <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr><td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#797978\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#797978;width:600px\" role=\"none\"><tr><td align=\"left\" style=\"Margin:0;padding-top:30px;padding-bottom:30px;padding-left:40px;padding-right:40px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#E9E9E9;font-size:16px\"><img src=\"https://eeprwjn.stripocdn.email/content/guids/8093320e-7fb1-4319-8ec9-59e4500fed4f/images/creativaremovebgpreview.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"280\" class=\"adapt-img\" height=\"280\"></a> </td></tr><tr>\n" +
                    "<td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><h1 style=\"Margin:0;line-height:60px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:40px;font-style:normal;font-weight:bold;color:#E9E9E9\">mail de confirmation</h1></td></tr></table></td></tr> <tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:2px;border-style:solid;border-color:#4ca2f8;border-radius:20px;background-image:url(https://eeprwjn.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png);background-repeat:no-repeat;background-position:left center\" background=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png\" role=\"presentation\"><tr>\n" +
                    "<td align=\"left\" class=\"es-m-p20r es-m-p20l\" style=\"padding:40px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:27px;color:#E9E9E9;font-size:18px\">Nous espérons que ce message vous trouve bien. Afin de garantir la sécurité de votre compte Creativa, nous vous envoyons un code de vérification unique.</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\"><strong>Veuillez utiliser le code ci-dessous pour confirmer votre identité :</strong></p>"+"<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\">Votre code de vérification est : <strong>" + verificationCode + "</strong></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\"><strong></strong></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\"><br><strong>&nbsp;</strong></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#E9E9E9;font-size:19px\"><strong></strong></p>\n" +
                    "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px\"><br></p></td></tr></table></td></tr></table></td></tr> <tr><td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px\">Assurez-vous de ne pas partager ce code avec quiconque, car il est destiné à des fins de sécurité personnelles. Si vous n'avez pas récemment tenté de vérifier votre compte ou si vous recevez ce message par erreur, veuillez nous en informer immédiatement.</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px\">Merci de votre coopération. Nous sommes là pour vous assurer une expérience sécurisée et agréable sur Creativa.</p>\n" +
                    "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px;display:none\">&nbsp;</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px;display:none\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px;display:none\"><br></p></td></tr> </table></td></tr></table></td></tr></table></td></tr></table> <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr>\n" +
                    "<td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#666666\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#666666;width:600px\" role=\"none\"><tr><td align=\"left\" background=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px;background-image:url(https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png);background-repeat:no-repeat;background-position:right top\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"left\" class=\"es-m-p0r es-m-p0l\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px\">merci,<br><strong>hajer hamrouni , equipe cretiva</strong></p> </td></tr></table></td></tr></table></td></tr></table></td></tr></table>\n" +
                    " <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"><tr><td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"><tr><td align=\"left\" bgcolor=\"#c8bca9\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px;background-color:#c8bca9\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"left\" style=\"padding:0;Margin:0;width:560px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0\"><table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Facebook\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/facebook-circle-white.png\" alt=\"Fb\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a> </td>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Twitter\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/twitter-circle-white.png\" alt=\"Tw\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                    " <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Instagram\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/instagram-circle-white.png\" alt=\"Inst\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                    " <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Youtube\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/youtube-circle-white.png\" alt=\"Yt\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td></tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></div></body></html>";

            String htmlContent = "<p>Votre code de vérification est : <strong>" + verificationCode + "</strong></p>";
            htmlPart.setContent(htmlContent1, "text/html");

            // Add the parts to the multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);

            // Set the content of the message
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("E-mail de vérification envoyé avec succès."+verificationCode);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Gérer les erreurs d'envoi d'e-mail
        }
    }
    public static void sendResetPasswordToken(String toEmail, String resetPasswordToken) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Réinitialisation de mot de passe");
            message.setText("");
            Multipart multipart = new MimeMultipart();

            // Part 1: Text content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(resetPasswordToken);

            // Part 2: HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"fr\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\"><head><meta charset=\"UTF-8\"><meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"><meta name=\"x-apple-disable-message-reformatting\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta content=\"telephone=no\" name=\"format-detection\"><title>Nouveau message</title> <!--[if (mso 16)]><style type=\"text/css\">     a {text-decoration: none;}     </style><![endif]--> <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> <!--[if gte mso 9]><xml> <o:OfficeDocumentSettings> <o:AllowPNG></o:AllowPNG> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml>\n" +
                    "<![endif]--> <!--[if !mso]><!-- --><link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"> <!--<![endif]--><style type=\"text/css\">#outlook a { padding:0;}.es-button { mso-style-priority:100!important; text-decoration:none!important;}a[x-apple-data-detectors] { color:inherit!important; text-decoration:none!important; font-size:inherit!important; font-family:inherit!important; font-weight:inherit!important; line-height:inherit!important;}.es-desk-hidden { display:none; float:left; overflow:hidden; width:0; max-height:0; line-height:0; mso-hide:all;}@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:36px!important; text-align:left } h2 { font-size:28px!important; text-align:left } h3 { font-size:20px!important; text-align:left }\n" +
                    " .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:28px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important }\n" +
                    " .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:18px!important; display:block!important; border-right-width:0px!important; border-left-width:0px!important; border-top-width:15px!important; border-bottom-width:15px!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important }\n" +
                    " .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important }\n" +
                    " .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important }\n" +
                    " .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }@media screen and (max-width:384px) {.mail-message-content { width:414px!important } }</style>\n" +
                    " </head>\n" +
                    " <body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"><div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"fr\" style=\"background-color:#F9F4FF\"> <!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" src=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" color=\"#F9F4FF\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill> </v:background><![endif]--><table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" background=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-image:url(https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png);background-repeat:repeat;background-position:center top;background-color:#F9F4FF\" role=\"none\"><tr>\n" +
                    "<td valign=\"top\" style=\"padding:0;Margin:0\"><table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"><tr><td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"><tr><td align=\"left\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:40px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"left\" style=\"padding:0;Margin:0;width:560px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img src=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/group.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"40\" height=\"40\"></a> </td></tr></table></td></tr></table></td></tr></table></td></tr></table>\n" +
                    " <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr><td align=\"center\" style=\"padding:0;Margin:0\"><table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#fef7eb;width:600px\" bgcolor=\"#fef7eb\" role=\"none\"><tr><td align=\"left\" style=\"Margin:0;padding-top:30px;padding-bottom:30px;padding-left:40px;padding-right:40px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#E9E9E9;font-size:16px\"><img src=\"https://eeprwjn.stripocdn.email/content/guids/8093320e-7fb1-4319-8ec9-59e4500fed4f/images/creativaremovebgpreview.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"280\" class=\"adapt-img\" height=\"280\"></a> </td></tr><tr>\n" +
                    "<td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:51px;color:#333333;font-size:34px\"><strong>Code de Réinitialisation de Mot de Passe pour compte Creativa</strong></p><h1 style=\"Margin:0;line-height:60px;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;font-size:40px;font-style:normal;font-weight:bold;color:#333333\"><br></h1></td></tr></table></td></tr> <tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:2px;border-style:solid;border-color:#4ca2f8;border-radius:20px;background-image:url(https://eeprwjn.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png);background-repeat:no-repeat;background-position:left center\" background=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png\" role=\"presentation\"><tr>\n" +
                    "<td align=\"left\" class=\"es-m-p20r es-m-p20l\" style=\"padding:40px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:36px;color:#333333;font-size:24px\">Cher(e) utilisateur(trice) de Creativa,</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:36px;color:#333333;font-size:24px\">Nous avons reçu votre demande de réinitialisation de mot de passe pour votre compte Creativa. Afin de garantir la sécurité de votre compte, veuillez utiliser le code de réinitialisation ci-dessous :</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:27px;color:#333333;font-size:18px\"><br></p>\n" +
                    "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><br></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><strong>Veuillez utiliser le code ci-dessous pour confirmer votre identité :</strong></p>"+"<p  style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\">Votre code de vérification est : <strong>" + resetPasswordToken + "</strong></p>"+"<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><strong></strong><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><strong></strong><br></p>\n" +
                    "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><br></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><strong></strong></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><br><strong>&nbsp;</strong></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:29px;color:#333333;font-size:19px\"><strong></strong></p>\n" +
                    "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px\"><br></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px\"><br></p></td></tr></table></td></tr></table></td></tr> <tr><td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px\">Assurez-vous de choisir un mot de passe robuste et unique pour protéger votre compte. Si vous n'avez pas demandé cette réinitialisation, veuillez nous contacter immédiatement à [adresse e-mail du support] pour prendre des mesures supplémentaires.</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px\">Nous sommes là pour vous aider à retrouver l'accès à votre compte Creativa. Merci de votre confiance..</p>\n" +
                    "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px;display:none\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px;display:none\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#333333;font-size:16px;display:none\"><br></p></td></tr></table></td></tr> </table></td></tr></table></td></tr></table> <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"><tr>\n" +
                    "<td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#666666\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#666666;width:600px\" role=\"none\"><tr><td align=\"left\" background=\"https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px;background-image:url(https://eeprwjn.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png);background-repeat:no-repeat;background-position:right top\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"left\" class=\"es-m-p0r es-m-p0l\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:40px;padding-right:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;color:#E9E9E9;font-size:16px\">merci,<br><strong>hajer hamrouni , equipe cretiva</strong></p> </td></tr></table></td></tr></table></td></tr></table></td></tr></table>\n" +
                    " <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"><tr><td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"><tr><td align=\"left\" bgcolor=\"#c8bca9\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px;background-color:#c8bca9\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"left\" style=\"padding:0;Margin:0;width:560px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0\"><table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Facebook\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/facebook-circle-white.png\" alt=\"Fb\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a> </td>\n" +
                    "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Twitter\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/twitter-circle-white.png\" alt=\"Tw\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                    " <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Instagram\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/instagram-circle-white.png\" alt=\"Inst\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                    " <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Youtube\" src=\"https://eeprwjn.stripocdn.email/content/assets/img/social-icons/circle-white/youtube-circle-white.png\" alt=\"Yt\" height=\"24\" width=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td></tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></td></tr></table></div></body></html>";

//            String htmlContent = "<p>Votre code de vérification est : <strong>" + verificationCode + "</strong></p>";
            htmlPart.setContent(htmlContent1, "text/html");

            // Add the parts to the multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);

            // Set the content of the message
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("E-mail de vérification envoyé avec succès."+resetPasswordToken);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle email sending errors
        }
    }


    public static void envoyerEmailConfirmation(User user) {


        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Créer une session de messagerie avec l'authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Créer le message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Confirmation d'inscription");
            message.setText("");
            Multipart multipart = new MimeMultipart();

            // Part 1: Text content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("");

            // Part 2: HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();


            // Part 2: HTML co
            String htmlContent1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"fr\">\n" +
                    " <head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                    "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                    "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                    "  <title>Nouveau message</title><!--[if (mso 16)]>\n" +
                    "    <style type=\"text/css\">\n" +
                    "    a {text-decoration: none;}\n" +
                    "    </style>\n" +
                    "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                    "<xml>\n" +
                    "    <o:OfficeDocumentSettings>\n" +
                    "    <o:AllowPNG></o:AllowPNG>\n" +
                    "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                    "    </o:OfficeDocumentSettings>\n" +
                    "</xml>\n" +
                    "<![endif]--><!--[if !mso]><!-- -->\n" +
                    "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"><!--<![endif]--><!--[if mso]>\n" +
                    " <style type=\"text/css\">\n" +
                    "     ul {\n" +
                    "  margin: 0 !important;\n" +
                    "  }\n" +
                    "  ol {\n" +
                    "  margin: 0 !important;\n" +
                    "  }\n" +
                    "  li {\n" +
                    "  margin-left: 47px !important;\n" +
                    "  }\n" +
                    " </style><![endif]\n" +
                    "-->\n" +
                    "  <style type=\"text/css\">\n" +
                    ".rollover:hover .rollover-first {\n" +
                    "  max-height:0px!important;\n" +
                    "  display:none!important;\n" +
                    "  }\n" +
                    "  .rollover:hover .rollover-second {\n" +
                    "  max-height:none!important;\n" +
                    "  display:block!important;\n" +
                    "  }\n" +
                    "  .rollover span {\n" +
                    "  font-size:0px;\n" +
                    "  }\n" +
                    "  u + .body img ~ div div {\n" +
                    "  display:none;\n" +
                    "  }\n" +
                    "  #outlook a {\n" +
                    "  padding:0;\n" +
                    "  }\n" +
                    "  span.MsoHyperlink,\n" +
                    "span.MsoHyperlinkFollowed {\n" +
                    "  color:inherit;\n" +
                    "  mso-style-priority:99;\n" +
                    "  }\n" +
                    "  a.es-button {\n" +
                    "  mso-style-priority:100!important;\n" +
                    "  text-decoration:none!important;\n" +
                    "  }\n" +
                    "  a[x-apple-data-detectors] {\n" +
                    "  color:inherit!important;\n" +
                    "  text-decoration:none!important;\n" +
                    "  font-size:inherit!important;\n" +
                    "  font-family:inherit!important;\n" +
                    "  font-weight:inherit!important;\n" +
                    "  line-height:inherit!important;\n" +
                    "  }\n" +
                    "  .es-desk-hidden {\n" +
                    "  display:none;\n" +
                    "  float:left;\n" +
                    "  overflow:hidden;\n" +
                    "  width:0;\n" +
                    "  max-height:0;\n" +
                    "  line-height:0;\n" +
                    "  mso-hide:all;\n" +
                    "  }\n" +
                    "  .es-button-border:hover > a.es-button {\n" +
                    "  color:#ffffff!important;\n" +
                    "  }\n" +
                    "@media only screen and (max-width:600px) {.es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } *[class=\"gmail-fix\"] { display:none!important } p, a { line-height:150%!important } h1, h1 a { line-height:120%!important } h2, h2 a { line-height:120%!important } h3, h3 a { line-height:120%!important } h4, h4 a { line-height:120%!important } h5, h5 a { line-height:120%!important } h6, h6 a { line-height:120%!important } h1 { font-size:36px!important; text-align:left } h2 { font-size:28px!important; text-align:left } h3 { font-size:20px!important; text-align:left } h4 { font-size:24px!important; text-align:left } h5 { font-size:20px!important; text-align:left } h6 { font-size:16px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:28px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-header-body h4 a, .es-content-body h4 a, .es-footer-body h4 a { font-size:24px!important } .es-header-body h5 a, .es-content-body h5 a, .es-footer-body h5 a { font-size:20px!important } .es-header-body h6 a, .es-content-body h6 a, .es-footer-body h6 a { font-size:16px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock a { font-size:12px!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3, .es-m-txt-c h4, .es-m-txt-c h5, .es-m-txt-c h6 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3, .es-m-txt-r h4, .es-m-txt-r h5, .es-m-txt-r h6 { text-align:right!important } .es-m-txt-j, .es-m-txt-j h1, .es-m-txt-j h2, .es-m-txt-j h3, .es-m-txt-j h4, .es-m-txt-j h5, .es-m-txt-j h6 { text-align:justify!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3, .es-m-txt-l h4, .es-m-txt-l h5, .es-m-txt-l h6 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-m-txt-r .rollover:hover .rollover-second, .es-m-txt-c .rollover:hover .rollover-second, .es-m-txt-l .rollover:hover .rollover-second { display:inline!important } .es-m-txt-r .rollover span, .es-m-txt-c .rollover span, .es-m-txt-l .rollover span { line-height:0!important; font-size:0!important } .es-spacer { display:inline-table } a.es-button, button.es-button { font-size:18px!important; line-height:120%!important } a.es-button, button.es-button, .es-button-border { display:block!important } .es-m-fw, .es-m-fw.es-fw, .es-m-fw .es-button { display:block!important } .es-m-il, .es-m-il .es-button, .es-social, .es-social td, .es-menu { display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .adapt-img { width:100%!important; height:auto!important } .es-mobile-hidden, .es-hidden { display:none!important } .es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } .es-social td { padding-bottom:10px } .h-auto { height:auto!important } a.es-button, button.es-button { border-top-width:15px!important; border-bottom-width:15px!important } }\n" +
                    "@media screen and (max-width:384px) {.mail-message-content { width:414px!important } }\n" +
                    "</style>\n" +
                    " </head>\n" +
                    " <body class=\"body\" style=\"width:100%;height:100%;padding:0;Margin:0\">\n" +
                    "  <div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"fr\" style=\"background-color:#F9F4FF\"><!--[if gte mso 9]>\n" +
                    "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                    "\t\t\t\t<v:fill type=\"tile\" src=\"https://tlr.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" color=\"#F9F4FF\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill>\n" +
                    "\t\t\t</v:background>\n" +
                    "\t\t<![endif]-->\n" +
                    "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" background=\"https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-image:url(https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png);background-repeat:repeat;background-position:center top;background-color:#F9F4FF\" role=\"none\">\n" +
                    "     <tr>\n" +
                    "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-right:20px;padding-bottom:40px;padding-left:20px\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img src=\"https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/group.png\" alt=\"\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\" width=\"40\"></a></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table>\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#1B1B1B;width:600px\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" style=\"Margin:0;padding-top:30px;padding-right:40px;padding-bottom:30px;padding-left:40px\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#E9E9E9;font-size:16px\"><img src=\"https://ffthidw.stripocdn.email/content/guids/CABINET_fac54106adbb7d65055eec3e43680e9555a729cefb0b1481e2895795a114e785/images/creativaremovebgpreview.png\" alt=\"\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\" width=\"280\" class=\"adapt-img\"></a></td>\n" +
                    "                     </tr>\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><h1 style=\"Margin:0;font-family:Poppins, sans-serif;mso-line-height-rule:exactly;letter-spacing:0;font-size:40px;font-style:normal;font-weight:bold;line-height:60px;color:#E9E9E9\"><strong>Bienvenue parmi nous !</strong></h1></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:2px;border-style:solid;border-color:#4ca2f8;border-radius:20px;background-image:url(https://ffthidw.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png);background-repeat:no-repeat;background-position:left center\" background=\"https://ffthidw.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png\" role=\"presentation\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"left\" class=\"es-m-p20r es-m-p20l\" style=\"padding:40px;Margin:0\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">Nous sommes ravis de vous accueillir en tant que nouvelle membre de Creativa, la communauté artistique vibrante où la créativité s'épanouit. Pour commencer, suivez ces étapes simples afin de vous intégrer pleinement à notre plateforme :</p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p>\n" +
                    "                       <ol style=\"font-family:Poppins, sans-serif;padding:0px 0px 0px 40px;margin:15px 0px\">\n" +
                    "                        <li style=\"color:#E9E9E9;margin:0px 0px 15px;font-size:16px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px;mso-margin-top-alt:15px\"><strong>Accédez à Votre Compte :</strong> Connectez-vous à votre compte en utilisant vos identifiants récemment créés.</p></li>\n" +
                    "                        <li style=\"color:#E9E9E9;margin:0px 0px 15px;font-size:16px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><strong>Explorez la Galerie :</strong> Plongez dans notre galerie virtuelle pour découvrir une myriade de projets artistiques stimulants. Appréciez le travail de talentueux artistes et explorez différentes formes d'expression.</p></li>\n" +
                    "                        <li style=\"color:#E9E9E9;margin:0px 0px 15px;font-size:16px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><strong>Personnalisez Votre Profil :</strong> Faites de votre profil un espace qui vous représente. &nbsp;Cela aidera les autres membres à mieux vous connaître.</p></li>\n" +
                    "                        <li style=\"color:#E9E9E9;margin:0px 0px 15px;font-size:16px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><strong>Connectez-vous avec la Communauté :</strong> Rejoignez les discussions, partagez vos idées et établissez des liens avec d'autres membres passionnés d'art. La communauté Creativa est un espace d'échange et de soutien.</p></li>\n" +
                    "                        <li style=\"color:#E9E9E9;margin:0px 0px 15px;font-size:16px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px;mso-margin-bottom-alt:15px\"><strong>Soutenez les Artistes :</strong> Si vous découvrez des projets qui vous captivent, n'hésitez pas à les soutenir. Vos likes et commentaires sont des moyens formidables d'encourager la créativité</p></li>\n" +
                    "                       </ol><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br><strong> </strong></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br> &nbsp;</p></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-right:40px;padding-left:40px\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">Si vous avez des questions ou avez besoin d'assistance, notre équipe de support est là pour vous aider. N'hésitez pas à nous contacter</p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">Nous sommes impatients de voir comment votre présence enrichira notre communauté. Merci de faire partie de Creativa, où l'art prend vie à travers la diversité et l'inspiration.</p></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table>\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#1B1B1B;width:600px\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" background=\"https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png\" style=\"padding:0;Margin:0;padding-top:20px;padding-right:40px;padding-left:40px;background-image:url(https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png);background-repeat:no-repeat;background-position:right top\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"left\" class=\"es-m-p0r es-m-p0l\" style=\"padding:0;Margin:0;padding-top:20px;padding-right:40px;padding-left:40px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">merci,<br><strong>hajer hamrouni,equipe creativa</strong></p></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table>\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#824f4f\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#824f4f;width:600px\" role=\"none\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" bgcolor=\"#77c82a\" style=\"Margin:0;padding-top:20px;padding-right:20px;padding-left:20px;padding-bottom:20px;background-color:#77c82a\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\n" +
                    "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                         <tr>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Facebook\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/facebook-circle-white.png\" alt=\"Fb\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"X.com\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/x-circle-white.png\" alt=\"X\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Instagram\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/instagram-circle-white.png\" alt=\"Inst\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Youtube\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/youtube-circle-white.png\" alt=\"Yt\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                         </tr>\n" +
                    "                       </table></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table></td>\n" +
                    "     </tr>\n" +
                    "   </table>\n" +
                    "  </div>\n" +
                    " </body>\n" +
                    "</html>";

            htmlPart.setContent(htmlContent1, "text/html");

            // Add the parts to the multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);
            // Set the content of the message
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("E-mail de confirmation envoyé avec succès.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi de l'e-mail de confirmation.");
        }
    }

    public static void sendBlockConfirmationEmail(int time,int id) {
        UserService S =new UserService();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Créer une session de messagerie avec l'authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Créer le message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(S.getById(id).getEmail()));
            message.setSubject("bloquage du compte");
            message.setText("");
            Multipart multipart = new MimeMultipart();

            // Part 1: Text content
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("");

            // Part 2: HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();


            // Part 2: HTML co
            String htmlContent1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"fr\">\n" +
                    " <head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                    "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                    "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                    "  <title>Nouveau message</title><!--[if (mso 16)]>\n" +
                    "    <style type=\"text/css\">\n" +
                    "    a {text-decoration: none;}\n" +
                    "    </style>\n" +
                    "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                    "<xml>\n" +
                    "    <o:OfficeDocumentSettings>\n" +
                    "    <o:AllowPNG></o:AllowPNG>\n" +
                    "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                    "    </o:OfficeDocumentSettings>\n" +
                    "</xml>\n" +
                    "<![endif]--><!--[if !mso]><!-- -->\n" +
                    "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                    "  <style type=\"text/css\">\n" +
                    ".rollover:hover .rollover-first {\n" +
                    "  max-height:0px!important;\n" +
                    "  display:none!important;\n" +
                    "  }\n" +
                    "  .rollover:hover .rollover-second {\n" +
                    "  max-height:none!important;\n" +
                    "  display:block!important;\n" +
                    "  }\n" +
                    "  .rollover span {\n" +
                    "  font-size:0px;\n" +
                    "  }\n" +
                    "  u + .body img ~ div div {\n" +
                    "  display:none;\n" +
                    "  }\n" +
                    "  #outlook a {\n" +
                    "  padding:0;\n" +
                    "  }\n" +
                    "  span.MsoHyperlink,\n" +
                    "span.MsoHyperlinkFollowed {\n" +
                    "  color:inherit;\n" +
                    "  mso-style-priority:99;\n" +
                    "  }\n" +
                    "  a.es-button {\n" +
                    "  mso-style-priority:100!important;\n" +
                    "  text-decoration:none!important;\n" +
                    "  }\n" +
                    "  a[x-apple-data-detectors] {\n" +
                    "  color:inherit!important;\n" +
                    "  text-decoration:none!important;\n" +
                    "  font-size:inherit!important;\n" +
                    "  font-family:inherit!important;\n" +
                    "  font-weight:inherit!important;\n" +
                    "  line-height:inherit!important;\n" +
                    "  }\n" +
                    "  .es-desk-hidden {\n" +
                    "  display:none;\n" +
                    "  float:left;\n" +
                    "  overflow:hidden;\n" +
                    "  width:0;\n" +
                    "  max-height:0;\n" +
                    "  line-height:0;\n" +
                    "  mso-hide:all;\n" +
                    "  }\n" +
                    "  .es-button-border:hover > a.es-button {\n" +
                    "  color:#ffffff!important;\n" +
                    "  }\n" +
                    "@media only screen and (max-width:600px) {.es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } *[class=\"gmail-fix\"] { display:none!important } p, a { line-height:150%!important } h1, h1 a { line-height:120%!important } h2, h2 a { line-height:120%!important } h3, h3 a { line-height:120%!important } h4, h4 a { line-height:120%!important } h5, h5 a { line-height:120%!important } h6, h6 a { line-height:120%!important } h1 { font-size:36px!important; text-align:left } h2 { font-size:28px!important; text-align:left } h3 { font-size:20px!important; text-align:left } h4 { font-size:24px!important; text-align:left } h5 { font-size:20px!important; text-align:left } h6 { font-size:16px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:28px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-header-body h4 a, .es-content-body h4 a, .es-footer-body h4 a { font-size:24px!important } .es-header-body h5 a, .es-content-body h5 a, .es-footer-body h5 a { font-size:20px!important } .es-header-body h6 a, .es-content-body h6 a, .es-footer-body h6 a { font-size:16px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock a { font-size:12px!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3, .es-m-txt-c h4, .es-m-txt-c h5, .es-m-txt-c h6 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3, .es-m-txt-r h4, .es-m-txt-r h5, .es-m-txt-r h6 { text-align:right!important } .es-m-txt-j, .es-m-txt-j h1, .es-m-txt-j h2, .es-m-txt-j h3, .es-m-txt-j h4, .es-m-txt-j h5, .es-m-txt-j h6 { text-align:justify!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3, .es-m-txt-l h4, .es-m-txt-l h5, .es-m-txt-l h6 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-m-txt-r .rollover:hover .rollover-second, .es-m-txt-c .rollover:hover .rollover-second, .es-m-txt-l .rollover:hover .rollover-second { display:inline!important } .es-m-txt-r .rollover span, .es-m-txt-c .rollover span, .es-m-txt-l .rollover span { line-height:0!important; font-size:0!important } .es-spacer { display:inline-table } a.es-button, button.es-button { font-size:18px!important; line-height:120%!important } a.es-button, button.es-button, .es-button-border { display:block!important } .es-m-fw, .es-m-fw.es-fw, .es-m-fw .es-button { display:block!important } .es-m-il, .es-m-il .es-button, .es-social, .es-social td, .es-menu { display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .adapt-img { width:100%!important; height:auto!important } .es-mobile-hidden, .es-hidden { display:none!important } .es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } .es-social td { padding-bottom:10px } .h-auto { height:auto!important } a.es-button, button.es-button { border-top-width:15px!important; border-bottom-width:15px!important } }\n" +
                    "@media screen and (max-width:384px) {.mail-message-content { width:414px!important } }\n" +
                    "</style>\n" +
                    " </head>\n" +
                    " <body class=\"body\" style=\"width:100%;height:100%;padding:0;Margin:0\">\n" +
                    "  <div dir=\"ltr\" class=\"es-wrapper-color\" lang=\"fr\" style=\"background-color:#F9F4FF\"><!--[if gte mso 9]>\n" +
                    "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                    "\t\t\t\t<v:fill type=\"tile\" src=\"https://tlr.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" color=\"#F9F4FF\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill>\n" +
                    "\t\t\t</v:background>\n" +
                    "\t\t<![endif]-->\n" +
                    "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" background=\"https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-image:url(https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png);background-repeat:repeat;background-position:center top;background-color:#F9F4FF\" role=\"none\">\n" +
                    "     <tr>\n" +
                    "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-right:20px;padding-bottom:40px;padding-left:20px\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img src=\"https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/group.png\" alt=\"\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\" width=\"40\"></a></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table>\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#4e0202\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#4e0202;width:600px\" role=\"none\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" style=\"Margin:0;padding-top:30px;padding-right:40px;padding-bottom:30px;padding-left:40px\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#E9E9E9;font-size:16px\"><img src=\"https://ffthidw.stripocdn.email/content/guids/CABINET_fac54106adbb7d65055eec3e43680e9555a729cefb0b1481e2895795a114e785/images/creativaremovebgpreview.png\" alt=\"\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\" width=\"280\" class=\"adapt-img\"></a></td>\n" +
                    "                     </tr>\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><h1 style=\"Margin:0;font-family:Poppins, sans-serif;mso-line-height-rule:exactly;letter-spacing:0;font-size:40px;font-style:normal;font-weight:bold;line-height:60px;color:#E9E9E9\"><strong>Votre compte a été bloqué </strong></h1></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-width:2px;border-style:solid;border-color:#4ca2f8;border-radius:20px;background-image:url(https://ffthidw.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png);background-repeat:no-repeat;background-position:left center\" background=\"https://ffthidw.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png\" role=\"presentation\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"left\" class=\"es-m-p20r es-m-p20l\" style=\"padding:40px;Margin:0\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">J'espère que ce message vous trouve bien. Nous vous contactons pour vous informer que votre compte a été temporairement bloqué.</p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">Votre compte restera bloqué pendant les prochaines <strong>"+time+" minutes</strong> &nbsp;Nous comprenons que cela peut être inconfortable, mais c'est une mesure de sécurité nécessaire pour protéger votre compte et nos utilisateurs.</p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br><strong>&nbsp;</strong></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br> &nbsp;</p></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-right:40px;padding-left:40px\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">Si vous avez des questions ou avez besoin d'assistance, notre équipe de support est là pour vous aider. N'hésitez pas à nous contacter</p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\"><br></p><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">Nous sommes impatients de voir comment votre présence enrichira notre communauté. Merci de faire partie de Creativa, où l'art prend vie à travers la diversité et l'inspiration.</p></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table>\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#1B1B1B;width:600px\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" background=\"https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png\" style=\"padding:0;Margin:0;padding-top:20px;padding-right:40px;padding-left:40px;background-image:url(https://ffthidw.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png);background-repeat:no-repeat;background-position:right top;background-color:#2e0606\" bgcolor=\"#2e0606\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"left\" class=\"es-m-p0r es-m-p0l\" style=\"padding:0;Margin:0;padding-top:20px;padding-right:40px;padding-left:40px\"><p style=\"Margin:0;mso-line-height-rule:exactly;font-family:Poppins, sans-serif;line-height:24px;letter-spacing:0;color:#E9E9E9;font-size:16px\">merci,<br><strong>hajer hamrouni,equipe creativa</strong></p></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table>\n" +
                    "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:100%;table-layout:fixed !important;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                    "         <tr>\n" +
                    "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                    "           <table bgcolor=\"#824f4f\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#824f4f;width:600px\" role=\"none\">\n" +
                    "             <tr>\n" +
                    "              <td align=\"left\" bgcolor=\"#5a0404\" style=\"Margin:0;padding-top:20px;padding-right:20px;padding-left:20px;padding-bottom:20px;background-color:#5a0404\">\n" +
                    "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"none\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                 <tr>\n" +
                    "                  <td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\n" +
                    "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                     <tr>\n" +
                    "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\n" +
                    "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                    "                         <tr>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Facebook\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/facebook-circle-white.png\" alt=\"Fb\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"X.com\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/x-circle-white.png\" alt=\"X\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Instagram\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/instagram-circle-white.png\" alt=\"Inst\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"mso-line-height-rule:exactly;text-decoration:underline;color:#333333;font-size:12px\"><img title=\"Youtube\" src=\"https://ffthidw.stripocdn.email/content/assets/img/social-icons/circle-white/youtube-circle-white.png\" alt=\"Yt\" height=\"24\" style=\"display:block;font-size:16px;border:0;outline:none;text-decoration:none\"></a></td>\n" +
                    "                         </tr>\n" +
                    "                       </table></td>\n" +
                    "                     </tr>\n" +
                    "                   </table></td>\n" +
                    "                 </tr>\n" +
                    "               </table></td>\n" +
                    "             </tr>\n" +
                    "           </table></td>\n" +
                    "         </tr>\n" +
                    "       </table></td>\n" +
                    "     </tr>\n" +
                    "   </table>\n" +
                    "  </div>\n" +
                    " </body>\n" +
                    "</html>";

            htmlPart.setContent(htmlContent1, "text/html");

            // Add the parts to the multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);
            // Set the content of the message
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("E-mail de bloquage envoyé avec succès.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi de l'e-mail de bloquage.");
        }
    }
}
