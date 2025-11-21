import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * Kernel implementation of SkillTree.
 *
 * Name: Rijul Rastogi Dot Number: Rastogi.68 Due Date: 10/23/25
 *
 * Representation: this.skills is a map from skill name -> Record, where each
 * Record contains: - prereqs: Set<String> of direct prerequisites - unlocked:
 * boolean state.
 *
 * Convention: - For every skill k in this.skills, its prereqs set contains only
 * names that also exist as keys in this.skills. - No cycles exist in the
 * prerequisite graph.
 *
 * Correspondence: - allSkills = DOMAIN(this.skills) - prerequisitesOf(s) =
 * this.skills[s].prereqs - isUnlocked(s) = this.skills[s].unlocked
 */
public class SkillTree1L extends SkillTreeSecondary {

    /**
     * Private record for storing skill data.
     */
    private static class Record {
        /**
         * Set of prerequisite skill names.
         */
        private Set<String> prereqs;
        /**
         * Whether the skill is unlocked.
         */
        private boolean unlocked;

        /**
         * Default constructor.
         */
        Record() {
            this.prereqs = new Set1L<>();
            this.unlocked = false;
        }
    }

    /**
     * Representation: map from skill → Record.
     */
    private Map<String, Record> skills;

    /**
     * Standard constructor.
     */
    public SkillTree1L() {
        this.createNewRep();
    }

    /**
     * Creates a new representation for this skill tree.
     */
    private void createNewRep() {
        this.skills = new Map1L<>();
    }

    @Override
    public final void addSkill(String name) {
        assert !this.skillExists(
                name) : "Violation of: skill does not already exist";

        Record r = new Record();
        this.skills.add(name, r);
    }

    @Override
    public final void addDependency(String skill, String prerequisite) {
        assert this.skillExists(skill) : "Violation of: skillExists(skill)";
        assert this.skillExists(
                prerequisite) : "Violation of: skillExists(prerequisite)";

        Record r = this.skills.value(skill);
        r.prereqs.add(prerequisite);
    }

    @Override
    public final void removeSkill(String skill) {
        assert this.skillExists(skill) : "Violation of: skillExists(skill)";

        this.skills.remove(skill);

        for (Map.Pair<String, Record> p : this.skills) {
            Record r = p.value();
            if (r.prereqs.contains(skill)) {
                r.prereqs.remove(skill);
            }
        }
    }

    @Override
    public final boolean skillExists(String skill) {
        return this.skills.hasKey(skill);
    }

    @Override
    public final boolean isUnlocked(String skill) {
        assert this.skillExists(skill) : "Violation of: skillExists(skill)";

        return this.skills.value(skill).unlocked;
    }

    @Override
    public final void unlockSkill(String skill) {
        assert this.skillExists(skill) : "Violation of: skillExists(skill)";

        this.skills.value(skill).unlocked = true;
    }

    @Override
    public final void resetSkills() {
        // Lock all skills
        for (Map.Pair<String, Record> p : this.skills) {
            p.value().unlocked = false;
        }
    }

    @Override
    public final Set<String> prerequisitesOf(String skill) {
        assert this.skillExists(skill) : "Violation of: skillExists(skill)";

        return this.skills.value(skill).prereqs;
    }

    @Override
    public final Set<String> allSkills() {
        Set<String> result = new Set1L<>();
        for (Map.Pair<String, Record> p : this.skills) {
            result.add(p.key());
        }

        return result;
    }

    /**
     * Clears this skill tree.
     */
    public final void clear() {
        this.createNewRep();
    }

    /**
     * Creates and returns a new instance of SkillTree1L.
     *
     * @return new instance of SkillTree1L
     */
    public final SkillTree1L newInstance() {
        return new SkillTree1L();
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
    public final String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        for (Map.Pair<String, Record> p : this.skills) {
            sb.append("  ").append(p.key()).append(" : prereqs=")
                    .append(p.value().prereqs).append(", unlocked=")
                    .append(p.value().unlocked).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
