import components.set.Set;
import components.set.Set1L;

/**
 * Secondary abstract class for {@code SkillTree}.
 *
 * Provides implementations for availableSkills and depth using only kernel
 * methods and standard methods.
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 11/11/25
 */
public abstract class SkillTreeSecondary implements SkillTree {

    /**
     * Returns all skills that can currently be unlocked.
     *
     * @return set of skills whose prerequisites are all unlocked
     * @ensures availableSkills = { s in allSkills | not isUnlocked(s) and all
     *          prereqs(s) are unlocked }
     */
    @Override
    public Set<String> availableSkills() {
        Set<String> result = new Set1L<>();
        Set<String> all = this.allSkills();

        for (String s : all) {
            if (!this.isUnlocked(s)) {
                boolean ready = true;
                Set<String> prereqs = this.prerequisitesOf(s);

                for (String p : prereqs) {
                    if (!this.isUnlocked(p)) {
                        ready = false;
                        break;
                    }
                }
                if (ready) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    /**
     * Returns the prerequisite depth of a skill.
     *
     * @param skill
     *            name of skill
     * @requires skillExists(skill)
     * @return number of levels of prerequisites
     * @ensures depth >= 0
     */
    @Override
    public int depth(String skill) {
        assert this.skillExists(skill) : "Skill must exist.";

        Set<String> prereqs = this.prerequisitesOf(skill);
        if (prereqs.size() == 0) {
            return 0;
        }

        int maxDepth = 0;
        for (String p : prereqs) {
            int d = this.depth(p);
            if (d > maxDepth) {
                maxDepth = d;
            }
        }

        return maxDepth + 1;
    }

    @Override
    public final int hashCode() {
        int h = 0;
        for (String s : this.allSkills()) {
            h += s.hashCode();
            h += this.prerequisitesOf(s).hashCode();
            if (this.isUnlocked(s)) {
                h++;
            }
        }
        return h;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof SkillTree1L)) {
            return false;
        }
        SkillTree1L other = (SkillTree1L) o;

        Set<String> mine = this.allSkills();
        Set<String> theirs = other.allSkills();

        if (!mine.equals(theirs)) {
            return false;
        }

        for (String s : mine) {
            if (this.isUnlocked(s) != other.isUnlocked(s)) {
                return false;
            }
            if (!this.prerequisitesOf(s).equals(other.prerequisitesOf(s))) {
                return false;
            }
        }
        return true;
    }
}
