package com.appointmentbooking.axpert.config;

import org.springframework.stereotype.Component;

@Component("email")
public class SendOtpEmail {

    public String getEmailContent(String otp) {
        String imgurl = "https://media.licdn.com/dms/image/D4E03AQEtdlYYnsTxPw/profile-displayphoto-shrink_800_800/0/1703879341715?e=1709769600&v=beta&t=NQE3KLcbc5qfGS7sd8FSQx_wLX8ikldXBCNp2LT8GEQ";
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Email Verification</title>\n" +
                "</head>\n" +
                "<body style=\"font-family: Arial, sans-serif;\">\n" +
                "\n" +
                "    <div style=\"background-color: #f4f4f4; padding: 20px;\">\n" +
                "        <h2>Email Verification</h2>\n" +
                "        <p>\n" +
                "            Dear User,<br><br>\n" +
                "            Thank you for registering. Please use the following OTP for verification:\n" +
                "        </p>\n" +
                "        \n" +
                "        <h1 style=\"font-size: 2em; margin: 10px 0;\">" + otp + "</h1>\n" +

                "<img src=\"" + imgurl + "\" alt=\"Additional Image\" style=\"width: 50px; height: 50px;\">\n"
                +
                "        \n" +
                "        <p>\n" +
                "            Note: This OTP is valid for a limited time.\n" +
                "        </p>\n" +
                "    </div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
