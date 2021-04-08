package httpTest;

class OutOfBoundsException extends Exception { 
    public OutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}

class ViewerNotFoundException extends Exception {
  public ViewerNotFoundException(String message) {
    super(message);
  }
}

class ApplicationException extends Exception {
  public ApplicationException(String message) {
    super(message);
  }
}