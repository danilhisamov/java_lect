package Lecture6;

import java.util.TreeMap;

public class Task3 {
    public static void main(String[] args) {
        String text = get1000Lorem();
        TreeMap<Character, Integer> map = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        System.out.println(map.toString());
    }

    private static String get1000Lorem() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque consequat faucibus varius. Suspendisse viverra diam ut justo sagittis, eu sollicitudin arcu sagittis. Aenean id tempor orci, sit amet auctor justo. Nunc dictum arcu urna, vitae gravida velit congue sed. Duis eget sollicitudin metus. Proin nulla mi, tempor a fermentum quis, accumsan nec eros. Nullam rhoncus, massa ut aliquet euismod, mauris metus tincidunt est, non malesuada est nisl ut mi. Donec egestas fermentum tortor, vel mattis sem tincidunt nec. Aliquam cursus eros ante, et varius dui varius ac. Quisque consectetur tincidunt varius. Nullam eget ullamcorper ligula, mattis elementum nunc. Duis eget fringilla nisi. Sed blandit, lectus vel faucibus dignissim, libero nibh ullamcorper arcu, vel euismod lacus dui eu nisl.\n" +
                "\n" +
                "Aenean hendrerit massa sed tincidunt viverra. Mauris lobortis pretium nibh ut dapibus. Duis eget ex quis mauris tempus tincidunt. Vivamus nec tortor sed erat ullamcorper blandit. Nunc id est venenatis, tempor metus quis, mollis turpis. Mauris nisl felis, gravida sed metus a, posuere tincidunt ipsum. Curabitur nec euismod velit, eu lacinia quam. Praesent justo ante, euismod non nisi id, porta ornare tellus. Nullam hendrerit ornare erat, non interdum est. Fusce iaculis non lorem vel cursus. Sed facilisis nibh mi, vitae venenatis nunc iaculis at. Integer suscipit augue nec magna tempus cursus. Sed mollis, nunc vitae maximus blandit, elit arcu pharetra quam, eu iaculis eros risus in magna. Duis rhoncus sapien at ipsum pellentesque faucibus. Aenean ut risus eleifend, efficitur est ac, maximus ante.\n" +
                "\n" +
                "Nam posuere ligula quis nunc mattis molestie. Sed vitae euismod ante, in elementum nunc. Nulla at aliquam dolor, sit amet sollicitudin est. Maecenas eu erat vitae massa semper euismod sit amet eu tellus. Aliquam finibus mattis odio, commodo efficitur nibh rutrum vel. Praesent sit amet neque quis quam fermentum molestie. Suspendisse finibus massa at enim maximus, at maximus arcu finibus. Pellentesque ligula felis, vestibulum sed velit sit amet, elementum semper libero. Donec a convallis nunc, vel pharetra nisl. Proin malesuada nec erat a pharetra.\n" +
                "\n" +
                "Morbi ornare lobortis augue et tincidunt. Nulla tempor tristique quam, vel varius urna iaculis non. Donec pulvinar leo non odio semper accumsan. Nunc porta dui lorem, non ultrices massa finibus ac. Suspendisse lacinia, mi sed gravida volutpat, lorem ante sagittis neque, sit amet eleifend velit nisl nec augue. Ut a nisl in libero mattis pretium nec et dui. Maecenas congue ligula ac ex hendrerit, sit amet porttitor enim egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed eget sem euismod, luctus risus sed, consequat justo. Aliquam erat volutpat. Suspendisse iaculis nulla ut pharetra egestas.\n" +
                "\n" +
                "Cras et leo non massa maximus pulvinar eu at felis. Sed hendrerit tincidunt luctus. Curabitur porta id sem nec euismod. Duis non velit eleifend, tincidunt ante vitae, interdum sem. Integer convallis, quam at sagittis aliquam, velit lorem dignissim sapien, ut condimentum orci ligula vitae erat. Nunc a rhoncus neque, eu ultrices mauris. Aliquam cursus urna et consequat auctor. Praesent pharetra vitae augue id pharetra. Nam semper eros sapien, ac mollis nisi consequat at. Praesent ut libero tincidunt, facilisis turpis vulputate, lacinia neque. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum ut gravida urna.\n" +
                "\n" +
                "Etiam non tincidunt nisl. Morbi et nisi vel nunc suscipit vestibulum et non quam. Donec viverra dapibus urna, quis sagittis nisl consequat in. Donec vel consequat urna, eget ullamcorper nisl. Morbi mattis, risus sed ultrices porttitor, lacus eros tincidunt sapien, rutrum ultrices arcu neque a eros. Fusce ut libero ex. Phasellus non imperdiet nulla, quis dignissim massa. Sed viverra enim arcu, id ullamcorper nulla ultricies non. Etiam facilisis mauris vitae eros ullamcorper lobortis. Nunc tincidunt, lacus non placerat suscipit, sem quam luctus ipsum, gravida congue tortor dolor ut dui. Suspendisse in tellus eu lorem rhoncus tristique. Nulla accumsan, arcu eu dignissim cursus, ante neque mollis lectus, vitae suscipit felis eros sed odio. Ut dui odio, auctor ac interdum sed, tincidunt sed nunc. Proin sit amet ipsum gravida, dapibus ex id, venenatis quam.\n" +
                "\n" +
                "Aenean condimentum congue risus. Nam pellentesque ac neque eget consectetur. Vestibulum bibendum maximus dolor sed consequat. Nullam nec sagittis ex. Aenean et turpis tempor, posuere urna at, venenatis arcu. Curabitur sed bibendum dolor. Nam congue nisi orci, a ultricies nibh rhoncus in. Fusce porta blandit enim. In laoreet nibh quis sollicitudin efficitur. Curabitur lorem ex, molestie ac euismod nec, pulvinar et tellus. Fusce tincidunt aliquam tortor id consectetur. Nullam mauris velit, molestie tincidunt suscipit vitae, dictum at dolor. Mauris tempor ornare nibh, quis molestie orci semper vel. Aliquam eget turpis convallis, gravida mauris ut, interdum felis. Pellentesque vel lectus et erat venenatis interdum eu lacinia risus. In iaculis eleifend diam ut tincidunt.\n" +
                "\n" +
                "Sed ipsum nisi, vulputate vel dui vel, tincidunt sagittis elit. Mauris non felis erat. Duis justo felis, tempus sed libero vitae, scelerisque hendrerit nulla. Phasellus blandit consectetur gravida. Quisque justo sem, tincidunt vel hendrerit vitae, tempus et nibh. Proin eros nunc, rhoncus sed mi id, mattis interdum ante. Donec placerat pulvinar velit id eleifend. Nullam dapibus orci et nisl mollis finibus. Integer laoreet metus id velit commodo venenatis. Vestibulum blandit fermentum ullamcorper. Proin at vestibulum sem. Nullam nec molestie urna.\n" +
                "\n" +
                "Sed ut dolor egestas, lacinia velit et, fringilla purus. Duis congue ipsum vitae elit suscipit vehicula. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec eu quam erat. Donec pretium, quam feugiat malesuada porta, ipsum orci interdum nisi, vel aliquet lectus dui eget mauris. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis dolor magna, venenatis in congue ac, laoreet ac eros. Nullam tincidunt, ipsum id rhoncus tincidunt, elit nisl ullamcorper quam, et malesuada elit quam at urna. Duis tortor lectus, tempor eget faucibus nec, condimentum a odio. Vestibulum sit amet tincidunt est, sed accumsan sapien. Morbi ultricies aliquam leo ut egestas. Morbi volutpat risus nec imperdiet maximus. Curabitur consectetur nisi nisi, nec congue nunc placerat nec. Donec tristique nunc ut tortor efficitur commodo. Duis et ligula sit amet metus iaculis tempor. Maecenas pellentesque luctus tortor non dapibus. Nulla vitae diam sapien.";
    }
}
