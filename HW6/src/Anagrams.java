import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Anagrams {
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
            37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    public Anagrams(){
        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
     }
     private void buildLetterTable(){
        letterTable = new HashMap<Character, Integer>();
         for(int i = 0; i < primes.length; i++) {
             letterTable.put((char) (i + 'a'), primes[i]);
         }
     }
     private void addWord(String s){
        if(s == null || s.length() == 0){
            throw new NullPointerException("Empty string");
        }
        Long hashCode = myHashCode(s);
        if(anagramTable.containsKey(hashCode)){
            anagramTable.get(hashCode).add(s);
        }else{
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            anagramTable.put(hashCode, list);
        }
     }
     private Long myHashCode(String s){
        long hashCode = 1;
        try{
            for(int i = 0; i < s.length(); i++){
                hashCode *= letterTable.get(s.toLowerCase().charAt(i));
            }
        }catch(NullPointerException e){
            throw new NullPointerException("Empty String");
        }
        return hashCode;
     }
     private void processFile(String s) throws IOException{
         FileInputStream fstream = new FileInputStream(s);
         BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
         String strLine;
         while((strLine = br.readLine()) != null){
            this.addWord(strLine);
         }
         br.close();
     }
     private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
        ArrayList<Map.Entry<Long,ArrayList<String>>> list = new ArrayList<>();
        int max = 0;
        for(Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()){
            if(entry.getValue().size() > max){
                list.clear();
                list.add(entry);
                max = entry.getValue().size();
            }else if(entry.getValue().size() == max){
                list.add(entry);
            }
        }
        return list;
    }
    public static void main(String[] args){
        Anagrams a = new Anagrams();
        final long startTime  =System.nanoTime();
        try{
            a.processFile("words_alpha.txt");
        }catch(IOException e1){
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime / 1E9);
        System.out.println("Elapsed Time: " + seconds);
        System.out.println("Key of maximum anagrams: " + maxEntries.get(0).getKey());
        System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
        System.out.println("Length of list of max anagrams : " + maxEntries.get(0).getValue().size());
    }
}
