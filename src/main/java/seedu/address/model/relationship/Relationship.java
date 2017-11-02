package seedu.address.model.relationship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.address.model.person.Name;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * This class defines the relationship between two ReadOnlyPersons
 */
public class Relationship {

    private ReadOnlyPerson fromPerson;
    private ReadOnlyPerson toPerson;
    private RelationshipDirection direction;

    private Name name;
    private ConfidenceEstimate confidenceEstimate;

    public Relationship(ReadOnlyPerson fromPerson, ReadOnlyPerson toPerson, RelationshipDirection direction) {
        requireAllNonNull(fromPerson, toPerson, direction);
        this.fromPerson = fromPerson;
        this.toPerson = toPerson;
        this.direction = direction;
        this.name = Name.UNSPECIFIED;
        this.confidenceEstimate = ConfidenceEstimate.UNSPECIFIED;
    }

    public Relationship(ReadOnlyPerson fromPerson, ReadOnlyPerson toPerson, RelationshipDirection direction, Name name, ConfidenceEstimate confidenceEstimate) {
        this(fromPerson, toPerson, direction);
        this.setConfidenceEstimate(confidenceEstimate);
        this.setName(name);
    }

    public ReadOnlyPerson getFromPerson() {
        return fromPerson;
    }

    public ReadOnlyPerson getToPerson() {
        return toPerson;
    }

    public RelationshipDirection getDirection() {
        return direction;
    }

    public Name getName() { return name; }

    public ConfidenceEstimate getConfidenceEstimate() {
        return confidenceEstimate;
    }

    public boolean isUndirected() {
        return !direction.isDirected();
    }

    /**
     * This is to find the opposite relationships of the one that the user is trying to add.
     * An opposite relationship of one relationship is defined as a relationship involving the same two persons
     * but with a different direction (DIRECTED <=> UNDIRECTED).
     * If the opposite relationship exists, remove the opposite relationship before adding the intended relationship.
     * @return an ArrayList containing two opposite relationships of this one.
     */
    public ArrayList<Relationship> oppositeRelationships() {
        ReadOnlyPerson fromPerson = getFromPerson();
        ReadOnlyPerson toPerson = getToPerson();
        ArrayList<Relationship> oppoRelationships = new ArrayList<>(2);
        if (this.isUndirected()) {
            oppoRelationships.add(new Relationship(fromPerson, toPerson, RelationshipDirection.DIRECTED));
            oppoRelationships.add(new Relationship(toPerson, fromPerson, RelationshipDirection.DIRECTED));
        } else {
            oppoRelationships.add(new Relationship(fromPerson, toPerson, RelationshipDirection.UNDIRECTED));
            oppoRelationships.add(new Relationship(toPerson, fromPerson, RelationshipDirection.UNDIRECTED));
        }

        return oppoRelationships;
    }

    public void setName(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    public void setConfidenceEstimate(ConfidenceEstimate confidenceEstimate) {
        requireNonNull(confidenceEstimate);
        this.confidenceEstimate = confidenceEstimate;
    }

    public String toString() {
        String nameAndCE = this.name.toString() + " " + this.confidenceEstimate.toString();
        if (isUndirected()) {
            return fromPerson.toString() + " <-> " + toPerson.toString() + " " + nameAndCE;
        } else {
            return fromPerson.toString() + " -> " + toPerson.toString() + " " + nameAndCE;
        }
    }


    @Override
    public boolean equals(Object other) {
        boolean correspondingPersonCheck = true;
        if (this.getDirection() != ((Relationship) other).getDirection()) {
            return false;
        } else {
            if (this.isUndirected() && ((Relationship) other).isUndirected()) {
                correspondingPersonCheck = (this.getFromPerson().equals(((Relationship) other).getFromPerson())
                        && this.getToPerson().equals(((Relationship) other).getToPerson()))
                        || (this.getFromPerson().equals(((Relationship) other).getToPerson())
                                && this.getToPerson().equals(((Relationship) other).getFromPerson()));
            } else if (!this.isUndirected() && !((Relationship) other).isUndirected()) {
                correspondingPersonCheck = this.getFromPerson().equals(((Relationship) other).getFromPerson())
                        && this.getToPerson().equals(((Relationship) other).getToPerson());
            }
        }

        return other == this // short circuit if same object
                || (other instanceof Relationship // instanceof handles nulls
                && correspondingPersonCheck);
    }
}
