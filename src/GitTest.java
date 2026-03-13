public class GitTest {
    private int test;
    public GitTest(int x){
        this.test = x;
    }
    public int getTest(){
        return this.test;
    }
    public int addAndGetTest(int add){
        test += add;
        return test;
    }
}
