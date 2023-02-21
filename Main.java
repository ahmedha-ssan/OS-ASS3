
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Partition> AllPartitions = new ArrayList<Partition>();
		ArrayList<Process> AllProcesses = new ArrayList<Process>();

		ArrayList<Partition> AllPartitions2 = new ArrayList<Partition>();
		// taking Partitions info
		System.out.println("Enter Number Of Partition:");
		int partiotions=scan.nextInt();
		for(int i=0;i<partiotions;i++) {
			Partition p=new Partition();
			System.out.println("Enter name of Partition"+(i+1)+":");
			p.Name=scan.next();
			System.out.println("Enter size of Partition"+(i+1)+":");
			p.size=scan.nextInt();
			p.userInput=true;
			AllPartitions.add(p);
			AllPartitions2.add(p);
		}
		// taking processes info
		System.out.println("Enter Number Of Processes:");
		int processes=scan.nextInt();
		for(int i=0;i<processes;i++) {
			Process p=new Process();
			System.out.println("Enter name of process"+(i+1)+":");
			p.name=scan.next();
			System.out.println("Enter size of process"+(i+1)+":");
			p.size=scan.nextInt();
			AllProcesses.add(p);
		}

		System.out.println("Select the policy you want to apply:");
		boolean cont=true;
		while(cont) {
			System.out.println("1. First fit");
			System.out.println("2. Worst fit");
			System.out.println("3. Best fit");
			System.out.println("4. exit");
			int option=scan.nextInt();
			switch(option) {
				case 1:
					FirstFit f = new FirstFit(AllPartitions, AllProcesses);
					f.firstfitLogic();
					AllPartitions = f.reset(AllPartitions2);
					break;
				case 2:
					WorstFit W = new WorstFit(AllPartitions, AllProcesses);
					W.addWorstFit();
					AllPartitions = W.reset(AllPartitions2);
					break;
				case 3:
					BestFit B = new BestFit(AllPartitions, AllProcesses);
					B.addBestFit();
					AllPartitions = B.reset(AllPartitions2);
					break;
				case 4:
					cont=false;
					break;
			}
		}
	}
}/*
6
Partition0
90
Partition1
20
Partition2
5
Partition3
30
Partition4
120
Partition5
80
4
Process1
15
Process2
90
Process3
30
Process4
100*/