import components.standard.Standard;

/**
 * Kernel interface for {@code SkillTree} component.
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/23/25
 */
public interface SkillTreeKernel extends Standard<SkillTree> {

    /**
     * Adds a new skill to the tree.
     *
     * @param name
     *            name of the skill to add
     * @requires name is not already in the tree
     * @ensures skill with name {@code name} is added and initially locked
     */
    void addSkill(String name);

    /**
     * Adds a prerequisite dependency between two skills.
     *
     * @param skill
     *            the skill that depends on another
     * @param prerequisite
     *            the required skill that must be unlocked first
     * @requires {@code skill} and {@code prerequisite} exist and are distinct
     * @ensures {@code prerequisite} is recorded as a dependency of
     *          {@code skill}
     */
    void addDependency(String skill, String prerequisite);

    /**
     * Unlocks a skill if all its prerequisites are met.
     *
     * @param skill
     *            name of skill to unlock
     * @requires {@code skill} exists in the tree
     * @ensures skill is unlocked if all its prerequisites are unlocked
     */
    void unlockSkill(String skill);

    /**
     * Reports whether a given skill is unlocked.
     *
     * @param skill
     *            name of skill
     * @requires {@code skill} exists in the tree
     * @ensures isUnlocked = true if skill is unlocked
     * @return true if {@code skill} is unlocked, false otherwise
     */
    boolean isUnlocked(String skill);
}
