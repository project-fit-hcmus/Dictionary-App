
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FileHandler {
    // khởi tạo một file mới
    public void WriteToFile(String filename, String keyword) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(keyword);
            bw.write("/");
            bw.write(LocalDateTime.now().toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public ArrayList<History> ReadFile(String filename, String from, String to) {
        ArrayList<History> list = new ArrayList<History>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (!line.isEmpty()) {
                History temp = new History();
                int pos = line.indexOf("/");
                temp.setKeyWord(line.substring(0, pos));
                line = line.substring(pos + 1);

                // chỉ lấy ra phần ngày
                pos = line.indexOf('T');
                line = line.substring(0, pos);
                temp.setDateTime(line);

                if (!(from.compareToIgnoreCase(line) <= 0 && line.compareToIgnoreCase(to) <= 0)) {
                    line = br.readLine();
                    continue;
                }

                int i;
                for (i = 0; i < list.size(); ++i) {
                    if (list.get(i).getKeyWord().equals(temp.getKeyWord()))
                        break;
                }
                if (i < list.size())
                    list.get(i).setTimes(list.get(i).getTimes() + 1);
                else {
                    temp.setTimes(1);
                    list.add(temp);
                }
                line = br.readLine();
            }
            return list;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

}

// lưu lại lịch sử tìm kiếm ( chưa được sử dụng)
class History {
    private String keyword;
    private String datetime;
    private int times;

    public String getKeyWord() {
        return keyword;
    }

    public String getDateTime() {
        return datetime;
    }

    public int getTimes() {
        return times;
    }

    public void setKeyWord(String value) {
        keyword = value;
    }

    public void setDateTime(String value) {
        datetime = value;
    }

    public void setTimes(int value) {
        times = value;
    }

}