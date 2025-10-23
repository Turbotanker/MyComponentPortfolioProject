import java.util.Set;

/**
 * Enhanced interface for {@code SkillTree} component.
 *
 * <p>
 * Extends the {@code SkillTreeKernel} to provide additional functionality such
 * as viewing available skills, resetting progress, and calculating depth.
 * </p>
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/23/25
 */
public interface SkillTree extends SkillTreeKernel {

    /**
     * Returns all skills that can currently be unlocked (all prerequisites
     * met).
     *
     * @ensures availableSkills = set of all locked skills with all
     *          prerequisites unlocked
     * @return set of skills currently available for unlocking
     */
    Set<String> availableSkills();

    /**
     * Locks all skills in the tree.
     *
     * @ensures all skills in the tree are locked
     */
    void resetSkills();

    /**
     * Returns the number of prerequisite levels deep a skill is in the tree.
     *
     * @param skill
     *            name of skill
     * @requires {@code skill} exists in the tree
     * @ensures depth = number of prerequisite levels before {@code skill}
     * @return depth level as a non-negative integer
     */
    int depth(String skill);
}
