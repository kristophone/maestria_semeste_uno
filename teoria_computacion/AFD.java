import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AFD {

    // ∑ Alfabeto de símbolos de entrada.
    private static final List<String> E = List.of("0", "1");

    // Conjunto finito de estados
    private static final List<String> Q = List.of("A", "B", "C");

    // Estado inicial que pertenece a ∑
    private static final String q0 = "A";

    // F de Q - es el conjunto de estado finales de aceptación.
    private static final List<String> F = List.of("A", "B");

    private static final List<Node> nodeList = List.of(
            new Node("A", "1", "A"),
            new Node("A", "0", "B"),
            new Node("B", "1", "A"),
            new Node("B", "0", "C"),
            new Node("C", "1", "C"),
            new Node("C", "0", "C")
    );

    private static final String[] word = "010101010w".split("");

    public static void main(String[] args) {

        // TODO: add validation on transitions

        if (!E.containsAll(Arrays.stream(word).collect(Collectors.toList()))) {
            System.out.println("la cadena ingresada no coincide con el alfabeto de entrada");
        } else {
            f(q0, 0, "");
        }
    }

    private static void f(String q, int pos, String road) {

        List<Node> nodes = nodeList.stream().filter(n -> n.getFrom().equals(q) && n.getSymbol().equals(word[pos])).collect(Collectors.toList());

        var pos1 = pos + 1;

        StringBuilder roadBuilder = new StringBuilder(road);

        if (word.length > pos1) {
            for (Node node : nodes) {
                roadBuilder.append(node.getFrom()).append("->").append(node.getTo()).append(",");
                f(node.getTo(), pos1, roadBuilder.toString());
            }
        } else {
            roadBuilder.append(nodes.get(nodes.size() - 1).getFrom()).append("->").append(nodes.get(nodes.size() - 1).getTo()).append(" ---> ");
            if (F.containsAll(nodes.stream().map(Node::getTo).collect(Collectors.toList()))) {
                System.out.println(roadBuilder.append("cadena aceptada"));
            } else {
                System.out.println(roadBuilder.append("cadena NO aceptada"));
            }
        }

    }

    private static class Node {

        private final String from;
        private final String symbol;
        private final String to;

        private Node(String from, String symbol, String to) {
            this.from = from;
            this.symbol = symbol;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getTo() {
            return to;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from='" + from + '\'' +
                    ", symbol='" + symbol + '\'' +
                    ", to='" + to + '\'' +
                    '}';
        }

        public String from() {
            return from;
        }

        public String symbol() {
            return symbol;
        }

        public String to() {
            return to;
        }

    }

}
