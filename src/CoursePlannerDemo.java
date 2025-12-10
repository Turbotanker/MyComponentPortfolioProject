/**
 * Demonstrates SkillTree as an academic course planner.
 *
 * @author Rijul Rastogi, Date: 12/10/25
 */
public final class CoursePlannerDemo {

    /**
     * Private constructor to prevent instantiation.
     */
    private CoursePlannerDemo() {
        // utility class
    }

    /**
     * Demonstrates SkillTree as an academic course planner.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {
        SkillTree planner = new SkillTree1L();

        planner.addSkill("CSE 2221");
        planner.addSkill("CSE 2231");
        planner.addSkill("CSE 2321");

        planner.addDependency("CSE 2231", "CSE 2221");
        planner.addDependency("CSE 2321", "CSE 2231");

        planner.unlockSkill("CSE 2221");

        System.out.println("Courses you can take next:");
        System.out.println(planner.availableSkills());
    }
}
