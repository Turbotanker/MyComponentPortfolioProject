import components.set.Set;

/**
 * Kernel interface for {@code SkillTree}.
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/23/25
 */
public interface SkillTreeKernel {

    /**
     * Adds a skill with no prerequisites.
     *
     * @param name
     *            name of skill
     * @updates this
     * @ensures skillExists(name)
     */
    void addSkill(String name);

    /**
     * Adds a prerequisite relationship: prerequisite -> skill.
     *
     * @param skill
     *            skill requiring the prerequisite
     * @param prerequisite
     *            prerequisite skill
     * @requires skillExists(skill) and skillExists(prerequisite)
     * @updates this
     * @ensures prerequisite is included in prerequisitesOf(skill)
     */
    void addDependency(String skill, String prerequisite);

    /**
     * Removes a skill entirely.
     *
     * @param skill
     *            name of skill
     * @requires skillExists(skill)
     * @updates this
     * @ensures not skillExists(skill)
     */
    void removeSkill(String skill);

    /**
     * Returns whether the skill exists.
     *
     * @param skill
     *            name of skill
     * @return true if skill exists, false otherwise
     */
    boolean skillExists(String skill);

    /**
     * Reports whether the skill is unlocked.
     *
     * @param skill
     *            name of skill
     * @requires skillExists(skill)
     * @return true if skill is unlocked, false otherwise
     */
    boolean isUnlocked(String skill);

    /**
     * Unlocks the given skill.
     *
     * @param skill
     *            name of skill
     * @requires skillExists(skill)
     * @updates this
     * @ensures isUnlocked(skill)
     */
    void unlockSkill(String skill);

    /**
     * Locks every skill.
     *
     * @updates this
     * @ensures for all s in allSkills, not isUnlocked(s)
     */
    void resetSkills();

    /**
     * Returns direct prerequisites of a skill.
     *
     * @param skill
     *            name of skill
     * @requires skillExists(skill)
     * @return set of prerequisites (aliased)
     */
    Set<String> prerequisitesOf(String skill);

    /**
     * Returns all skills in the tree.
     *
     * @return set of all skill names (aliased)
     */
    Set<String> allSkills();
}
