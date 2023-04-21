public class Person {
    private String name;
    private int age;
    public void setAge(int age) throws ImpossibleInfo { //throws kullanılınca hata olursa catch'e gider.
        if(age<17){
            throw new ImpossibleInfo("Yaş 18 veya daha buyuk olmalidir.");
        }
        this.age = age;
    }
    public void setName(String name) throws ImpossibleInfo { //throws kullanılınca hata olursa catch'e gider.
        if (name.length()<3){
            throw new ImpossibleInfo("Isim 3 karakterden az olamaz"); //Burada program sonlanır.Aşağıdaki kodlar çalışmaz.
        }
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public Person(String name, int age) throws ImpossibleInfo { //throws kullanılınca hata olursa catch'e gider.
        this.setName(name);
        this.setAge(age);
    }
    @Override
    public String toString(){
        return "Name: " + this.name + " Age: " + this.age;
    }
}
