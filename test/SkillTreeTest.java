import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for SkillTree.
 *
 * @author Rijul Rastogi, Date: 12/10/25
 */
public class SkillTreeTest {

    /**
     * Test depth on a simple chain A -> B -> C. B depends on A, C depends on B.
     */
    @Test
    public void testDepthLinearChain() {
        SkillTree t = new SkillTree1L();

        t.addSkill("A");
        t.addSkill("B");
        t.addSkill("C");

        t.addDependency("B", "A");
        t.addDependency("C", "B");

        assertEquals(0, t.depth("A"));
        assertEquals(1, t.depth("B"));
        assertEquals(2, t.depth("C"));
    }

    /**
     * Test availableSkills where only root skills are available initially.
     * Using A -> B -> C chain again.
     */
    @Test
    public void testAvailableSkillsInitial() {
        SkillTree t = new SkillTree1L();

        t.addSkill("A");
        t.addSkill("B");
        t.addSkill("C");

        t.addDependency("B", "A");
        t.addDependency("C", "B");

        // Initially, only A has no prereqs
        assertTrue(t.availableSkills().contains("A"));
        assertEquals(1, t.availableSkills().size());
    }

    /**
     * Test availableSkills after unlocking a prerequisite. After unlocking A, B
     * should become available.
     */
    @Test
    public void testAvailableSkillsAfterUnlock() {
        SkillTree t = new SkillTree1L();

        t.addSkill("A");
        t.addSkill("B");
        t.addSkill("C");

        t.addDependency("B", "A");
        t.addDependency("C", "B");

        t.unlockSkill("A");

        assertTrue(t.availableSkills().contains("B"));
    }

    /**
     * Test that availableSkills does not modify the state of the tree. We build
     * the chain, call availableSkills, then re-check depth.
     */
    @Test
    public void testAvailableSkillsDoesNotChangeState() {
        SkillTree t = new SkillTree1L();

        t.addSkill("A");
        t.addSkill("B");
        t.addSkill("C");

        t.addDependency("B", "A");
        t.addDependency("C", "B");

        t.availableSkills(); // should not change structure

        assertEquals(0, t.depth("A"));
        assertEquals(1, t.depth("B"));
        assertEquals(2, t.depth("C"));
    }

    /**
     * Test equals for two identical trees. equals/hashCode are implemented in
     * SkillTreeSecondary.
     */
    @Test
    public void testEqualsSameStructureAndState() {
        SkillTree t1 = new SkillTree1L();
        SkillTree t2 = new SkillTree1L();

        // Build same structure in both
        t1.addSkill("A");
        t1.addSkill("B");
        t1.addSkill("C");
        t1.addDependency("B", "A");
        t1.addDependency("C", "B");
        t1.unlockSkill("A");

        t2.addSkill("A");
        t2.addSkill("B");
        t2.addSkill("C");
        t2.addDependency("B", "A");
        t2.addDependency("C", "B");
        t2.unlockSkill("A");

        assertTrue(t1.equals(t2));
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    /**
     * Test equals detects difference in unlocked state.
     */
    @Test
    public void testEqualsDifferentUnlockedState() {
        SkillTree t1 = new SkillTree1L();
        SkillTree t2 = new SkillTree1L();

        t1.addSkill("A");
        t1.addSkill("B");
        t1.addDependency("B", "A");

        t2.addSkill("A");
        t2.addSkill("B");
        t2.addDependency("B", "A");

        t1.unlockSkill("A");
        // t2 leaves A locked

        assertTrue(!t1.equals(t2));
    }

    /**
     * Test equals detects different prerequisite sets.
     */
    @Test
    public void testEqualsDifferentPrereqs() {
        SkillTree t1 = new SkillTree1L();
        SkillTree t2 = new SkillTree1L();

        t1.addSkill("A");
        t1.addSkill("B");
        t1.addDependency("B", "A");

        t2.addSkill("A");
        t2.addSkill("B");
        // no dependency in t2

        assertTrue(!t1.equals(t2));
    }
}
