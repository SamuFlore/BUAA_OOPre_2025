import java.util.ArrayList;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void parse(ArrayList<Adventurer> advs) {
        parseAdventurer(advs);
        if (lexer.peek() != null) {
            return;
        }
    }

    public Adventurer parseAdventurer(ArrayList<Adventurer> advs) { // 解析冒险者
        String id = parseId();
        Adventurer adventurer = Adventurer.findAdv(advs, id); // master
        if (match("(")) {
            ArrayList<Adventurer> servants = parseServant(advs);
            for (Adventurer serv :  servants) {
                adventurer.addServant(serv); // 添加下级
            }
        }
        return adventurer;
    }

    public ArrayList<Adventurer> parseServant(ArrayList<Adventurer> advs) { // 被雇佣者
        ArrayList<Adventurer> servants = parseServList(advs);
        match(")");
        return servants;
    }

    public ArrayList<Adventurer> parseServList(ArrayList<Adventurer> advs) { // 被雇佣者序列
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(parseAdventurer(advs)); // 至少一个冒险者是下级
        while (match(",")) {
            adventurers.add(parseAdventurer(advs));
        }
        return adventurers;
    }

    public String parseId() {
        String currentToken = lexer.peek();
        lexer.next(); // 消费一个token
        return currentToken;
    }

    public boolean match(String dest) {
        String currentToken = lexer.peek();
        if (currentToken.equals(dest)) {
            lexer.next(); // 成功则消费 token
            return true;
        }
        return false;
    }
}

