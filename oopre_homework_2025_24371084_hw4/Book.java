public class Book {
    private String name;
    private String magic;

    public Book(String name, String magic) {
        this.name = name;
        this.magic = magic;
    }

    public String getName() {
        return name;
    }

    public String getMagic() {
        return magic;
    }

    @Override
    public Book clone() {
        return new Book(name, magic);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return book.name.equals(name) && book.magic.equals(magic);
    }

    public int getScore() {
        int res = 0;
        for (int l = 0; l < magic.length(); l++) {
            for (int r = l + 1; r < magic.length(); r++) {
                String s = magic.substring(l, r);
                int len = s.length();
                int num = 0;
                int alph = 0;
                int jing = 0;
                for (int i = 0; i < len; i++) {
                    if (Character.isDigit(s.charAt(i))) {
                        num++;
                    } else if (Character.isAlphabetic(s.charAt(i))) {
                        alph++;
                    } else if (s.charAt(i) == '#') {
                        jing++;
                    }
                }
                if (s.charAt(0) == '#' && s.charAt(len - 1) == '#'
                        && jing == 2 && num > 0
                        && num >= alph) {
                    res++;
                }
            }
        }
        return res;
    }

    public void addMagic(String magic) {
        this.magic += magic;
    }

    public void subMagic(int a, int b) {
        if (a >= magic.length()) {
            magic = "";
        }
        else if (a > b) {
            magic = "";
        }
        else {
            magic = magic.substring(a, Integer.min(b + 1, magic.length()));
        }
    }

    public boolean contains(String s) {
        return magic.contains(s);
    }
}
