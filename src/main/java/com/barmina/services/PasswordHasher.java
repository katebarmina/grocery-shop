package com.barmina.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher {
  private final String hashingAlgorithm = "SHA-512";

  public byte[] getSalt() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }

  public String hashPassword(String password, byte[] salt) {
    StringBuilder hashedPassword = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance(hashingAlgorithm);
      md.update(salt);
      byte[] digest = md.digest(password.getBytes());
      for (byte b : digest) {
        hashedPassword.append(String.format("%02x", b));
      }
    } catch (NoSuchAlgorithmException ex) {
      System.out.println("Couldn't hash password with SHA-512 algorithm");
    }
    return hashedPassword.toString();
  }
}
