package TEMP;

public class ConstructorStudent {
    String hakbun;
    String name;
    int kor, mat, eng;

    public ConstructorStudent(String hakbun, String name, int kor, int mat, int eng) {
        this.hakbun = hakbun;
        this.name = name;
        this.kor = kor;
        this.mat = mat;
        this.eng = eng;
    }

    public ConstructorStudent(String hakbun, String name) {
        this.hakbun = hakbun;
        this.name = name;
    }
    public int sum(){
        int sum = kor+mat+eng;
        return sum;
    }
    public double avg(){
        double avg = sum()/3;
        return avg;
    }
}
