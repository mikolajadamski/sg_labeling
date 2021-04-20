import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

class ConstraintProgrammingSimpleApp {

    public static void main(String[] args) {
        Model model = new Model("Choco Solver SEND MORE MONEY");
        IntVar S = model.intVar("S", 1,9);
        IntVar E = model.intVar("E", 0, 9);
        IntVar N = model.intVar("N", 0, 9);
        IntVar D = model.intVar("D", 0, 9);
        IntVar M = model.intVar("M", 1, 9);
        IntVar O = model.intVar("O", 0, 9);
        IntVar R = model.intVar("R", 0, 9);
        IntVar Y = model.intVar("Y", 0, 9);

        model.arithm(S.mul(1000).add(E.mul(100)).add(N.mul(10)).add(D).intVar(), "+",
                M.mul(1000).add(O.mul(100)).add(R.mul(10)).add(E).intVar(),"=",
                M.mul(10000).add(O.mul(1000)).add(N.mul(100)).add(E.mul(10)).add(Y).intVar())
                .post();

        model.allDifferent(S, E, N, D, M, O, R, Y).post();

        model.getSolver().solve();
        System.out.println("Solution: ");
        System.out.println(S +","+ E +","+ N + "," + D +","+ M +","+ O+"," + R +","+ Y);
    }
}
