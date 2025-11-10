import java.util.Set;

/**
 * Kernel interface for {@code SkillTree} component.
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/23/25
 */
public interface SkillTreeKernel {

    /**
     * Adds a new skill to the tree.
     *
     * @param name
     *            the name of the skill being added
     * @updates this
     * @ensures skillExists(name)
     */
    void addSkill(String name);

    /**
     * Adds a prerequisite relationship: prerequisite -> skill.
     *
     * @param skill
     *            the skill that depends on the prerequisite
     * @param prerequisite
     *            the prerequisite skill
     * @requires skillExists(skill) and skillExists(prerequisite)
     * @updates this
     * @ensures prerequisite is listed as a prerequisite of skill
     */
    void addDependency(String skill, String prerequisite);

    /**
     * Returns whether the given skill exists in the tree.
     *
     * @param skill
     *            the skill name
     * @return true if the skill exists, false otherwise
     */
    boolean skillExists(String skill);

    /**
     * Reports whether the given skill has been unlocked.
     *
     * @param skill
     *            the name of the skill
     * @requires skillExists(skill)
     * @return true if the skill is unlocked, false otherwise
     */
    boolean isUnlocked(String skill);

    /**
     * Unlocks a skill.
     *
     * @param skill
     *            the name of the skill
     * @requires skillExists(skill)
     * @updates this
     * @ensures isUnlocked(skill)
     */
    void unlockSkill(String skill);

    /**
     * Resets all skills to locked.
     *
     * @updates this
     * @ensures for all skills s, isUnlocked(s) == false
     */
    void resetSkills();

    /**
     * Returns the direct prerequisites of this skill.
     *
     * @param skill
     *            the name of the skill
     * @requires skillExists(skill)
     * @return a set of skills required for this skill
     * @aliases return value
     */
    Set<String> prerequisitesOf(String skill);

    /**
     * Returns the set of all skill names stored in this skill tree.
     *
     * @return a set containing all skill names
     * @aliases return value
     */
    Set<String> allSkills();
}
