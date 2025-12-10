import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for SkillTree1L.
 *
 * @author Rijul Rastogi, Date: 12/10/25
 */
public class SkillTree1LTest {

    /**
     * magic number for three.
     */
    private final int three = 3;

    /**
     * Test addSkill + skillExists for a single skill.
     */
    @Test
    public void testAddSkillSkillExistsOne() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Java");

        assertTrue(t.skillExists("Java"));
    }

    /**
     * Test addSkill with multiple skills.
     */
    @Test
    public void testAddSkillSkillExistsMultiple() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Java");
        t.addSkill("Python");
        t.addSkill("C++");

        assertTrue(t.skillExists("Java"));
        assertTrue(t.skillExists("Python"));
        assertTrue(t.skillExists("C++"));
    }

    /**
     * Test addDependency + prerequisitesOf.
     */
    @Test
    public void testAddDependency() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Data Structures");
        t.addSkill("Java");

        t.addDependency("Data Structures", "Java");

        assertTrue(t.prerequisitesOf("Data Structures").contains("Java"));
    }

    /**
     * Test removeSkill removes it from the tree.
     */
    @Test
    public void testRemoveSkillRemovesSkill() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Java");
        assertTrue(t.skillExists("Java"));

        t.removeSkill("Java");

        assertFalse(t.skillExists("Java"));
    }

    /**
     * Test removeSkill also removes it from others' prereq sets.
     */
    @Test
    public void testRemoveSkillRemovesFromPrereqs() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Java");
        t.addSkill("Data Structures");

        t.addDependency("Data Structures", "Java");
        t.removeSkill("Java");

        assertFalse(t.prerequisitesOf("Data Structures").contains("Java"));
    }

    /**
     * Test unlockSkill + isUnlocked.
     */
    @Test
    public void testUnlockSkillIsUnlocked() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Java");
        assertFalse(t.isUnlocked("Java"));

        t.unlockSkill("Java");

        assertTrue(t.isUnlocked("Java"));
    }

    /**
     * Test resetSkills locks everything.
     */
    @Test
    public void testResetSkillsLocksAll() {
        SkillTree t = new SkillTree1L();

        t.addSkill("Java");
        t.addSkill("Python");

        t.unlockSkill("Java");
        t.unlockSkill("Python");

        t.resetSkills();

        assertFalse(t.isUnlocked("Java"));
        assertFalse(t.isUnlocked("Python"));
    }

    /**
     * Test allSkills returns all names that were added.
     */
    @Test
    public void testAllSkills() {
        SkillTree t = new SkillTree1L();

        t.addSkill("A");
        t.addSkill("B");
        t.addSkill("C");

        assertTrue(t.allSkills().contains("A"));
        assertTrue(t.allSkills().contains("B"));
        assertTrue(t.allSkills().contains("C"));
        assertEquals(this.three, t.allSkills().size());
    }

    /**
     * Test transferFrom moves contents and empties source.
     */
    @Test
    public void testTransferFrom() {
        SkillTree1L source = new SkillTree1L();
        SkillTree1L target = new SkillTree1L();

        source.addSkill("Java");
        source.addSkill("Python");

        target.transferFrom(source);

        assertFalse(source.skillExists("Java"));
        assertFalse(source.skillExists("Python"));

        assertTrue(target.skillExists("Java"));
        assertTrue(target.skillExists("Python"));
    }
}
