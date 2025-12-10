/**
 * Demonstrates SkillTree as a game skill progression system.
 *
 * @author Rijul Rastogi, Date: 12/10/25
 */
public final class GameSkillDemo {

    /**
     * Private constructor to prevent instantiation.
     */
    private GameSkillDemo() {
        // utility class
    }

    /**
     * Demonstrates SkillTree as a game skill progression system.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {
        SkillTree skills = new SkillTree1L();

        skills.addSkill("Attack");
        skills.addSkill("Defense");
        skills.addSkill("Magic");

        skills.addDependency("Magic", "Attack");
        skills.addDependency("Magic", "Defense");

        System.out.println("Initially available:");
        System.out.println(skills.availableSkills());

        skills.unlockSkill("Attack");
        skills.unlockSkill("Defense");

        System.out.println("Available after unlocks:");
        System.out.println(skills.availableSkills());
    }
}
