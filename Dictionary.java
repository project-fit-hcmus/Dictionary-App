import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Word {

    private String word;
    private String tempWord;
    private List<Kind> listKind;
    private boolean isMark = false;

    public boolean getIsMark() {
        return isMark;
    }

    public void setIsMark(boolean value) {
        isMark = value;
    }

    public String getWord() {
        return word;
    }

    public List<Kind> getListKind() {
        return listKind;
    }

    public String getTempWord() {
        return tempWord;
    }

    public void setWord(String w) {
        word = w;
    }

    public void setTempWord(String mean) {
        tempWord = mean;
        String temp = mean;
        listKind = new ArrayList<Kind>();
        // xử lý để lấy dữ liệu từ temp đưa vào trong word và listKind
        int pos = temp.indexOf('\n');
        if (pos != -1)
            temp = temp.substring(pos + 1, temp.length());
        int i = 0;
        // tách các loại từ nếu có
        if (temp.charAt(0) == '*')
            temp = temp.substring(1, temp.length());

        int p = temp.indexOf('*');
        while (p != -1) {
            String tempKind = temp.substring(0, p);
            temp = temp.substring(p + 1, temp.length());
            Kind kind = new Kind();
            kind.setTempKind(tempKind);
            listKind.add(kind);
            p = temp.indexOf('*');

        }
        Kind kind = new Kind();
        kind.setTempKind(temp);
        listKind.add(kind);
    }

    public void setListKind(List<Kind> values) {
        listKind = values;

    }
}

class Kind {
    private String kind;
    private String tempKind;
    private List<Mean> definitions;

    public String getKind() {
        return kind;
    }

    public String getTempKind() {
        return tempKind;
    }

    public List<Mean> getDefinitions() {
        return definitions;
    }

    public void setKind(String value) {
        kind = value;
    }

    public void setDefinitions(ArrayList<Mean> values) {
        definitions = values;
    }

    public void setTempKind(String value) {
        tempKind = value;

        definitions = new ArrayList<Mean>();
        // xử lý thông tin để đưa vào Kind và list definition
        String temp = value;
        int pos = temp.indexOf('-');
        if (pos == -1) {
            kind = temp;
        } else {
            kind = temp.substring(0, pos);
            temp = temp.substring(pos + 1, temp.length());
            pos = temp.indexOf("- ");

            while (pos != -1) {
                Mean mean = new Mean();
                String tempMean = temp.substring(0, pos);
                temp = temp.substring(pos + 1, temp.length());
                mean.setTempMean(tempMean);
                definitions.add(mean);
                pos = temp.indexOf("- ");
            }
            Mean mean = new Mean();
            mean.setTempMean(temp);
            definitions.add(mean);
        }
    }
}

class Mean {
    private String mean;
    private String tempMean;
    private List<Example> listExample;

    public String getMean() {
        return mean;
    }

    public String getTempMean() {
        return tempMean;
    }

    public List<Example> getListExamples() {
        return listExample;
    }

    public void setMean(String value) {
        mean = value;
    }

    public void setListExamples(ArrayList<Example> values) {
        listExample = values;
    }

    public void setTempMean(String value) {
        tempMean = value;
        String temp = value;
        listExample = new ArrayList<Example>();
        // xử lý thông tin để lấy mean và list Example
        int pos = temp.indexOf('=');
        if (pos == -1) {
            mean = temp;

        } else {
            mean = temp.substring(0, pos);
            temp = temp.substring(pos + 1, temp.length());
            pos = temp.indexOf('=');
            while (pos != -1) {
                Example example = new Example();
                String tempex = temp.substring(0, pos);
                temp = temp.substring(pos + 1, temp.length());
                example.setTempEx(tempex);
                listExample.add(example);
                pos = temp.indexOf('=');
            }

            Example example = new Example();
            example.setTempEx(temp);
            listExample.add(example);
        }

    }
}

// lưu thông tin của một cặp ví dụ + nghĩa của nó
class Example {
    private String ex;
    private String trans;
    private String tempEx;

    public String getEx() {
        return ex;
    }

    public String getTempEx() {
        return tempEx;
    }

    public String getTrans() {
        return trans;
    }

    public void setEx(String value) {
        ex = value;
    }

    public void setTrans(String value) {
        trans = value;
    }

    public void setTempEx(String value) {
        tempEx = value;
        String temp = value;
        int pos = temp.indexOf('+');
        if (pos != -1) {
            ex = temp.substring(0, pos);
            trans = temp.substring(pos + 1, temp.length());
        } else {
            ex = temp;
            trans = "";
        }
    }
};

public class Dictionary extends DefaultHandler {

    private StringBuilder content;
    private Word word;
    private List<Word> listWord = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("record".equalsIgnoreCase(qName)) {
            word = new Word();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "record":
                listWord.add(word);
                word = null;
                content = null;
                break;
            case "word":
                word.setWord(content.toString());
                content = null;
                break;
            case "meaning":
                word.setTempWord(content.toString());
                content = null;
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (content == null) {
            content = new StringBuilder(new String(ch, start, length).trim());
        }
        content = content.append(new String(ch, start, length).trim());
    }

    public List<Word> getListWord() {
        return listWord;
    }

    public void setListWord(List<Word> list) {
        listWord = list;
    }

    /**
     * @param args the command line arguments
     */

    public void readData(String filedata) {
        try {
            File inputFile = new File(filedata);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParse = factory.newSAXParser();
            Dictionary dictionary = new Dictionary();

            saxParse.parse(inputFile, dictionary);
            listWord = dictionary.getListWord();
            System.out.println("Waiting ... ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}