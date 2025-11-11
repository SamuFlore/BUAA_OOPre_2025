public class Lexer {
    private final String input;
    private int pos = 0;
    private String curToken;

    private String removeWhitespace(String s) {
        return s.replaceAll("\\s+", "");
    }

    public Lexer(String input) {
        this.input = removeWhitespace(input);
        next();
    }

    private boolean isIdChar(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '_';
    }

    private String getID() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && isIdChar(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            ++pos;
        }
        return sb.toString();
    }

    public void next() {
        if (pos == input.length()) {
            curToken = null;
            return;
        }
        char c = input.charAt(pos);
        if (isIdChar(c)) {
            curToken = getID();
        }
        else if (c == '(' || c == ')' || c == ',') {
            pos += 1;
            curToken = String.valueOf(c);
        }
        else {
            throw new RuntimeException("Unexpected character: " + c);
        }
    }

    public String peek() {
        return curToken;
    }
}
