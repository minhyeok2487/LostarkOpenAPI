package MH.IcePang.domain.contents;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChaosDungeonRepository extends JpaRepository<ChaosDungeon,Integer> {

	@Query(value = "SELECT id, level,\n"
		+ "\tsum(counts) as counts, \n"
		+ "\tROUND(sum(shilling)/sum(counts),2) as shilling, \n"
		+ "\tROUND(sum(gold)/sum(counts),2) as gold,\n"
		+ "\tROUND(sum(honor_shard)/sum(counts),2) as honor_shard,\n"
		+ "\tROUND(sum(leapstone)/sum(counts),2) as leapstone,\n"
		+ "\tROUND(sum(destruction_stone)/sum(counts),2) as destruction_stone,\n"
		+ "\tROUND(sum(guardian_stone)/sum(counts),2) as guardian_stone,\n"
		+ "\tROUND(sum(jewelry)/sum(counts),2) as jewelry,\n"
		+ "\tROUND(sum(cube_ticket)/sum(counts),2) as cube_ticket,\n"
		+ "\tROUND(sum(hall_ticket)/sum(counts),2) as hall_ticket,\n"
		+ "\tROUND(sum(gold_room)/sum(counts),2) as gold_room,\n"
		+ "\tROUND(sum(boss_room)/sum(counts),2) as boss_room\n"
		+ "\tFROM chaos_dungeon_data group by level;", nativeQuery = true)
	List<ChaosDungeon> chaosDungeonStatisticsList();
}
