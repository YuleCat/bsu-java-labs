import java.util.Comparator;

/**
 * Created by Yule Cat on 17.12.2017.
 */
public class MyComparator implements Comparator<ExportedGoods> {
    @Override
    public int compare(ExportedGoods o1, ExportedGoods o2) {
        return o1.getNameOfGood().compareTo(o2.getNameOfGood());
    }
}
