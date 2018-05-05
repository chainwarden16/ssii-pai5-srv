package src.utils;

import org.json.simple.*;

public interface GenericSocket  {
    public JSONObject readLineFromSocket() throws GenericSocketException;
    public void writeLineToSocket(String line) throws GenericSocketException;
    public void closeSocket() throws GenericSocketException;

    public class GenericSocketException extends Exception{
        private static final long serialVersionUID = -4191496085897406080L;

		public GenericSocketException() {
            super();
        }

        public GenericSocketException(String message) {
            super(message);
        }
    }
}