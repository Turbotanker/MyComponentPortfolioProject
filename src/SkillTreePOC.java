import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Proof of concept for the SkillTree component. Models a branching set of
 * skills where some depend on others. Demonstrates state (skills/unlocked) and
 * behavior (unlocking, resetting etc).
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/17/25
 */
public class SkillTreePOC {

    /**
     * Internal Skill class to hold skill data.
     */
    private static class Skill {
        String name;
        List<String> prerequisites;
        boolean unlocked;

        Skill(String name) {
            this.name = name;
            this.prerequisites = new ArrayList<>();
            this.unlocked = false;
        }
    }

    /**
     * Maps each skill name to its Skill object.
     */
    private Map<String, Skill> skills;

    /**
     * Constructs an empty SkillTree.
     */
    public SkillTreePOC() {
        this.skills = new HashMap<>();
    }

    /* ===================== Kernel Methods ===================== */

    /**
     * Adds a new skill to the tree.
     *
     * @param name
     *            The name of the skill to add.
     */
    public void addSkill(String name) {
        if (!this.skills.containsKey(name)) {
            this.skills.put(name, new Skill(name));
        }
    }

    /**
     * Sets a dependency between two skills.
     *
     * @param skill
     *            The skill that has a prerequisite.
     * @param prerequisite
     *            The prerequisite skill.
     */
    public void addDependency(String skill, String prerequisite) {
        Skill s = this.skills.get(skill);
        Skill p = this.skills.get(prerequisite);
        if (s != null && p != null) {
            s.prerequisites.add(prerequisite);
        }
    }

    /**
     * Checks if a skill is unlocked.
     *
     * @param skill
     *            The skill to check.
     *
     * @return True if the skill is unlocked, false otherwise.
     */
    public boolean isUnlocked(String skill) {
        Skill s = this.skills.get(skill);
        return s != null && s.unlocked;
    }

    /**
     * Unlocks a skill if all its prerequisites are met.
     *
     * @param skill
     *            The skill to unlock.
     */
    public void unlockSkill(String skill) {
        boolean canUnlock = true;
        Skill s = this.skills.get(skill);
        if (s == null) {
            System.out.println("Skill not found: " + skill);
            canUnlock = false;
        } else {
            int i = 0;
            while (i < s.prerequisites.size()) {
                String prereq = s.prerequisites.get(i);
                if (!this.isUnlocked(prereq)) {
                    System.out.println("Cannot unlock " + skill + " (missing "
                            + prereq + ")");
                    canUnlock = false;
                }
                i++;
            }
        }
        if (canUnlock && s != null) {
            s.unlocked = true;
            System.out.println("Unlocked skill: " + skill);
        }
    }

    /* ===================== Secondary Methods ===================== */

    /**
     * Returns all skills that can currently be unlocked.
     *
     * @return Set of skill names that are available to unlock.
     */
    public Set<String> availableSkills() {
        Set<String> available = new HashSet<>();
        for (Skill s : this.skills.values()) {
            if (!s.unlocked) {
                boolean allMet = true;
                int i = 0;
                while (i < s.prerequisites.size()) {
                    String prereq = s.prerequisites.get(i);
                    if (!this.isUnlocked(prereq)) {
                        allMet = false;
                    }
                    i++;
                }

                if (allMet) {
                    available.add(s.name);
                }
            }
        }
        return available;
    }

    /** Resets all skills to locked state. */
    public void resetSkills() {
        for (Skill s : this.skills.values()) {
            s.unlocked = false;
        }
        System.out.println("All skills have been reset.");
    }

    /**
     * Returns how many levels deep a skill is in the dependency chain.
     *
     * @param skill
     *            The skill to check.
     *
     * @return Depth of the skill.
     */
    public int depth(String skill) {
        int result = 0;
        Skill s = this.skills.get(skill);

        if (s != null && !s.prerequisites.isEmpty()) {
            int maxDepth = 0;
            int i = 0;
            while (i < s.prerequisites.size()) {
                String prereq = s.prerequisites.get(i);
                int prereqDepth = 1 + this.depth(prereq);
                if (prereqDepth > maxDepth) {
                    maxDepth = prereqDepth;
                }
                i++;
            }
            result = maxDepth;
        }

        return result;
    }

    /**
     * Main Method.
     *
     * @param args
     *            Command line arguments
     *
     */
    public static void main(String[] args) {
        SkillTreePOC tree = new SkillTreePOC();

        // Add skills
        tree.addSkill("Basics");
        tree.addSkill("Intermediate");
        tree.addSkill("Advanced");

        // Add dependencies
        tree.addDependency("Intermediate", "Basics");
        tree.addDependency("Advanced", "Intermediate");

        // Show available skills before unlocking
        System.out.println("Available skills: " + tree.availableSkills());

        // Try unlocking in order
        tree.unlockSkill("Advanced"); // should fail
        tree.unlockSkill("Basics"); // should succeed
        tree.unlockSkill("Intermediate");
        tree.unlockSkill("Advanced");

        // Show current state
        System.out.println("Available skills: " + tree.availableSkills());
        System.out.println("Depth of 'Advanced': " + tree.depth("Advanced"));

        // Reset
        tree.resetSkills();
        System.out.println(
                "Available skills after reset: " + tree.availableSkills());
    }
}
