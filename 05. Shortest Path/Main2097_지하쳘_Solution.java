import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2097_���ϫx_Solution {
	
	static class Vertex implements Comparable<Vertex> {
		
		int vertex; // �츮�� �ʿ��� �� ����ȣ
		int distance; // distance�� PriorityQueue �ȿ��� �ּҰ��� poll�ϱ� ���� �ʿ���
		public Vertex(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("���� : ");
			builder.append(vertex);
			builder.append(", �Ÿ� : ");
			builder.append(distance);
			return builder.toString();
		}
		public int compareTo(Vertex other) {
			return this.distance-other.distance;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = Integer.parseInt(st.nextToken()) - 1;

		int[][] input = new int[n][n];
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];
		int[] pre=new int[n];
		final int INF = 99999;
		PriorityQueue<Vertex> queue = new PriorityQueue<>();	// �湮���� ���� ��� offer

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.fill(distance, INF);
		distance[start] = 0;
		queue.offer(new Vertex(start, distance[start]));
		
		while (!queue.isEmpty()) {
			
			Vertex nextVertex = queue.poll();
			if (visited[nextVertex.vertex]) continue;
			if (nextVertex.vertex == end) break;
			visited[nextVertex.vertex] = true;

			// b�ܰ� : a�ܰ迡�� ���õ� ������� �̹湮 ���� �����鿡 ���� ó��
			for (int j = 0; j < n; j++) {
				// �̹湮 �����̸鼭 ���� ������ ���
				if (!visited[j] && input[nextVertex.vertex][j] != 0) {
					if (nextVertex.distance + input[nextVertex.vertex][j] < distance[j]) {
						distance[j] = nextVertex.distance + input[nextVertex.vertex][j];
						pre[j] = nextVertex.vertex;
						queue.offer(new Vertex(j, distance[j]));
					}
				}
			}
			
		}
		
//		for (int i = 0; i < n; i++) { // a�ܰ�:�̹湮 ���� �� �ּ� ��Ż ����ġ��(����ȭ ��)�� ���� ������ ã��
//			 
//			int min = INF;
//			int nextNode = 0;
//
//			for (int j = 0; j < n; j++) {
//				if (!visited[j] && distance[j] < min) {
//					min = distance[j];
//					nextNode = j;
//				}
//			}
//			visited[nextNode] = true;
//			visitCount++;
//
//			if (visitCount == n) {
//				break;
//			}
//
//			// b�ܰ� : a�ܰ迡�� ���õ� ������� �̹湮 ���� �����鿡 ���� ó��
//			for (int j = 0; j < n; j++) {
//				// �̹湮 �����̸鼭 ���� ������ ���
//				if (!visited[j] && input[nextNode][j] != 0) {
//					if (distance[nextNode] + input[nextNode][j] < distance[j]) {
//						distance[j] = distance[nextNode] + input[nextNode][j];
//						pre[j]=nextNode;
//					}
//				}
//			}
//		}

		System.out.println(distance[end]);
		ArrayList<Integer> path=new ArrayList<>();
		path.add(end);
		
		int before=end;
		do{
			before=pre[before];
			path.add(0,before);
			
		}while(before!=0);
		
		for(Integer i :path){
			System.out.println(i+1+" ");
		}
		
	}

}
