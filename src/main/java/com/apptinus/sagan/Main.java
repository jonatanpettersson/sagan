package com.apptinus.sagan;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.apptinus.sagan.board.MoveGen;
import java.io.IOException;
import org.slf4j.LoggerFactory;

public class Main {
  private static Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

  public static final String VERSION = "v0.0.6";

  public static final int DEFAULT_TT_SIZE_MB = 16;

  public static void main(String[] args) throws IOException {

    // Populate the version from properties file

    // Handle arguments
    String startMode = "console";
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-debug")) {
          Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
          rootLogger.setLevel(Level.DEBUG);
        } else if (args[i].equals("-console")) {
          startMode = "console";
        }
      }
    }

    logger.info("Starting Sagan " + VERSION);
    MoveGen.init();

    if (startMode.equals("console")) {
      Uci.lineInput();
    } else if (startMode.equals("uci")) {
      Uci.uci();
    }
  }
}
