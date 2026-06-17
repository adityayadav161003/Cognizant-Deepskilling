interface Document { void open(); }
class PdfDoc implements Document { public void open() { System.out.println("Opening PDF."); } }
class WordDoc implements Document { public void open() { System.out.println("Opening Word."); } }

abstract class DocFactory { public abstract Document create(); }
class PdfFactory extends DocFactory { public Document create() { return new PdfDoc(); } }
class WordFactory extends DocFactory { public Document create() { return new WordDoc(); } }
