import java.util.ArrayList;
import java.util.Scanner;

public class FirstFit {
	ArrayList<Partition> AllPartitions;
	ArrayList<Process> AllProcesses;
	Scanner scan = new Scanner(System.in);
	static int counter;
	ArrayList<String> exceptions=new ArrayList<String>();
	public FirstFit(ArrayList<Partition> p, ArrayList<Process> p2) {
		AllPartitions=p;
		AllProcesses=p2;
		counter=AllPartitions.size();
	}
	public void firstfitLogic() {
		for(int i=0;i<AllProcesses.size();i++) {
			for(int j=0;j<AllPartitions.size();j++) {
				if(AllPartitions.get(j).size > AllProcesses.get(i).size && AllPartitions.get(j).processName==" " && AllProcesses.get(i).hasPlace==false) {
					AllPartitions.get(j).processName=AllProcesses.get(i).name;
					AllPartitions.get(j).processSize=AllProcesses.get(i).size;

					Partition newPartition = new Partition();

					newPartition.Name="Partition"+counter;
					newPartition.size=AllPartitions.get(j).size-AllPartitions.get(j).processSize;
					AllPartitions.add(j+1,newPartition);
					AllProcesses.get(i).hasPlace=true;
					counter++;
					break;
				}
				if(AllPartitions.get(j).size == AllProcesses.get(i).size && AllPartitions.get(j).processName==" " && AllProcesses.get(i).hasPlace==false) {
					AllPartitions.get(j).processName=AllProcesses.get(i).name;
					AllPartitions.get(j).processSize=AllProcesses.get(i).size;
					AllProcesses.get(i).hasPlace=true;
					break;
				}
			}
			if(AllProcesses.get(i).hasPlace==false) {
				exceptions.add(AllProcesses.get(i).name+" can not be allocated");
				AllProcesses.get(i).hasPlace=false;
			}
		}
		for(int i=0;i<AllPartitions.size();i++) {
			if(AllPartitions.get(i).processName!=" ")
				System.out.println(AllPartitions.get(i).Name+" ("+AllPartitions.get(i).processSize+" KB) => "+AllPartitions.get(i).processName);
			else 
				System.out.println(AllPartitions.get(i).Name+" ("+AllPartitions.get(i).size+" KB) => External fragment");
		}
		for(int i=0;i<exceptions.size();i++) {
			System.out.println(exceptions.get(i));
		}
		exceptions.clear();
		compaction();
		
	}
	public void compaction() {
		System.out.println("Do you want to compact? 1.yes 2.no");
		int option=scan.nextInt();
		if(option==1) {
			int initialsize=AllPartitions.size();
			int compactionSize=0;
			for(int i=0;i<AllPartitions.size();i++) {
				if(AllPartitions.get(i).processName==" ") {
					compactionSize+=AllPartitions.get(i).size;
					AllPartitions.remove(AllPartitions.get(i));
					i--;
				}
			}
			Partition newPartition = new Partition();
			newPartition.Name="Partition"+initialsize;
			newPartition.size=compactionSize;
			AllPartitions.add(newPartition);
			counter++;
			firstfitLogic();
		}
	}
	public ArrayList<Partition> reset(ArrayList<Partition> p){
		ArrayList<Partition> resetAllPartitions=  new ArrayList<Partition>();
		for(int i=0;i<p.size();i++) {
			if(p.get(i).userInput==true) {
				Partition newpart= new Partition();
				newpart.Name=p.get(i).Name;
				newpart.size=p.get(i).size;
				newpart.userInput=true;
				resetAllPartitions.add(newpart);
			}
		}
		for(int i=0;i<AllProcesses.size();i++) {
			AllProcesses.get(i).hasPlace=false;
		}
		return resetAllPartitions;
	}
}
