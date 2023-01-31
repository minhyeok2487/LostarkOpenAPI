package MH.IcePang.domain.items;

import MH.IcePang.domain.contents.ChaosDungeon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Items,Integer> {


}
