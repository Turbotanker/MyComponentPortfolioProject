import components.set.Set;

/**
 * Enhanced interface for {@code SkillTree}.
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/23/25
 */
public interface SkillTree extends SkillTreeKernel {

    /**
     * Returns all skills that can currently be unlocked.
     *
     * @return set of skills whose prerequisites are all unlocked
     * @ensures availableSkills = { s in allSkills | not isUnlocked(s) and all
     *          prereqs(s) are unlocked }
     */
    Set<String> availableSkills();

    /**
     * Locks all skills in the tree.
     *
     * @updates this
     * @ensures for all s in allSkills, not isUnlocked(s)
     */
    @Override
    void resetSkills();

    /**
     * Returns the prerequisite depth of a skill.
     *
     * @param skill
     *            name of skill
     * @requires skillExists(skill)
     * @return number of levels of prerequisites
     * @ensures depth >= 0
     */
    int depth(String skill);
}
