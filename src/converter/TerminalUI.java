package converter;

public class TerminalUI {
    // Reset
    public static final String RESET = "\u001B[0m";
    
    // Regular Colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // Background
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";
    
    // Bold
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    // Clear screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    // Print header
    public static void printHeader(String title) {
        String line = "═".repeat(title.length() + 4);
        System.out.println(BLUE + "╔" + line + "╗" + RESET);
        System.out.println(BLUE + "║" + CYAN + "  " + BOLD + title + "  " + RESET + BLUE + "║" + RESET);
        System.out.println(BLUE + "╚" + line + "╝" + RESET);
    }
    
    // Print success message
    public static void printSuccess(String message) {
        System.out.println(GREEN + "✓ " + message + RESET);
    }
    
    // Print error message
    public static void printError(String message) {
        System.out.println(RED + "✗ " + message + RESET);
    }
    
    // Print info message
    public static void printInfo(String message) {
        System.out.println(CYAN + "ℹ " + message + RESET);
    }
    
    // Print menu item
    public static void printMenuItem(int number, String title, String description) {
        System.out.printf(YELLOW + "%d. " + BOLD + "%s" + RESET + "\n   %s\n\n", number, title, description);
    }
    
    // Print separator
    public static void printSeparator() {
        System.out.println(BLUE + "─".repeat(60) + RESET);
    }
    
    // Get colored input prompt
    public static String getColoredPrompt(String prompt) {
        return YELLOW + "➤ " + CYAN + prompt + RESET + " ";
    }
    
    // Print result box
    public static void printResult(String label, String value) {
        String[] lines = value.split("\\r?\\n");
        int maxLength = Math.max(label.length() + 2, 50);
        
        // Top border
        System.out.println(BLUE + "┌─" + CYAN + " " + BOLD + label + RESET + BLUE + " " + "─".repeat(maxLength - label.length() - 1) + "┐" + RESET);
        
        // Content
        for (String line : lines) {
            System.out.println(BLUE + "│ " + WHITE + line + " ".repeat(maxLength - line.length()) + BLUE + " │" + RESET);
        }
        
        // Bottom border
        System.out.println(BLUE + "└" + "─".repeat(maxLength + 2) + "┘" + RESET);
    }
}
