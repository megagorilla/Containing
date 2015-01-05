package nhl.containing.client.entities.cranes;

import nhl.containing.client.ContainingClient;
import nhl.containing.client.entities.Container;
import nhl.containing.client.entities.Crane;

import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

/**
 *
 * @author Yannick
 */
public class DockingCrane extends Crane {

    Container container;
    MotionPath motionpath;
    MotionEvent motionevent;
    boolean Unloading = false;
    boolean isShipCrane;
    int ID;

    /**
     * Loads the models for the DockingCrane and puts them in a node
     *
     * @param qualtiy Changes the qualtiy of the crane (LOW,MEDIUM,HIGH)
     */
    public DockingCrane(ContainingClient.Quality qualtiy, boolean isShipCrane, int ID) {
        this.isShipCrane = isShipCrane;
        this.ID = ID;
        String craneBasePath = "Models/high/crane/dockingcrane/crane.j3o";
        String grabbingGearPath = "Models/high/crane/dockingcrane/grabbingGear.j3o";
        String grabbingGearHolderPath = "Models/high/crane/dockingcrane/grabbingGearHolder.j3o";
        String hookLeftPath = "Models/high/crane/dockingcrane/hookLeft.j3o";
        String hookRightPath = "Models/high/crane/dockingcrane/hookRight.j3o";

        switch (qualtiy) {
            case HIGH:
                craneBasePath = "Models/high/crane/dockingcrane/crane.j3o";
                grabbingGearPath = "Models/high/crane/dockingcrane/grabbingGear.j3o";
                grabbingGearHolderPath = "Models/high/crane/dockingcrane/grabbingGearHolder.j3o";
                hookLeftPath = "Models/high/crane/dockingcrane/hookLeft.j3o";
                hookRightPath = "Models/high/crane/dockingcrane/hookRight.j3o";
                break;
            case MEDIUM:
                craneBasePath = "Models/medium/crane/dockingcrane/crane.j3o";
                grabbingGearPath = "Models/medium/crane/dockingcrane/grabbingGear.j3o";
                grabbingGearHolderPath = "Models/medium/crane/dockingcrane/grabbingGearHolder.j3o";
                hookLeftPath = "Models/medium/crane/dockingcrane/hookLeft.j3o";
                hookRightPath = "Models/medium/crane/dockingcrane/hookRight.j3o";
                break;
            case LOW:
                craneBasePath = "Models/low/crane/dockingcrane/crane.j3o";
                grabbingGearPath = "Models/low/crane/dockingcrane/grabbingGear.j3o";
                grabbingGearHolderPath = "Models/low/crane/dockingcrane/grabbingGearHolder.j3o";
                hookLeftPath = "Models/low/crane/dockingcrane/hookLeft.j3o";
                hookRightPath = "Models/low/crane/dockingcrane/hookRight.j3o";
                break;
        }

        attachChild(ContainingClient.getMyAssetManager().loadModel(craneBasePath));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(grabbingGearPath));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(grabbingGearHolderPath));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(hookLeftPath));
        grabber.attachChild(ContainingClient.getMyAssetManager().loadModel(hookRightPath));
        grabber.setLocalTranslation(-28, -2, 0);
        grabber2.attachChild(grabber);
        attachChild(grabber2);
        ContainingClient.getMyRootNode().attachChild(this);
        if (isShipCrane) {
            this.rotate(0, FastMath.HALF_PI, 0);
            this.setLocalTranslation(40 * ID - 200, 0, 922);
        } else {
            this.rotate(0, FastMath.PI, 0);
            this.setLocalTranslation(400, 0, 570);
        }
    }

    public void connectContainer(Container container) {
        grabber.attachChild(container);
        container.setLocalTranslation(0, 11, 0);
    }

    public void getContainerFrom(final Vector3f location, final int ContainerID, final float dayLength) {
        if (isShipCrane) {
            Unloading = true;
            motionpath = new MotionPath();
            Vector3f loc = this.getLocalTranslation();
            motionpath.addWayPoint(this.getLocalTranslation());
            Vector3f loc2 = new Vector3f(location.x * Container.length, loc.y, loc.z);

            if (!loc2.equals(loc)) {
                motionpath.addWayPoint(loc2);
            } else {
                motionpath.addWayPoint(new Vector3f(loc2.x + 0.00001f, loc2.y, loc2.z));
            }

            motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
            motionpath.setCurveTension(0f);

            motionevent = new MotionEvent(this, motionpath);
            motionevent.setDirectionType(MotionEvent.Direction.None);
            if (dayLength < 10f) {
                motionevent.setInitialDuration(1f);
            } else if (dayLength >= 10f && dayLength < 30f) {
                motionevent.setInitialDuration(10f);
            } else if (dayLength >= 30f) {
                motionevent.setInitialDuration(30f);
            }
            motionevent.setSpeed(1f);
            motionevent.play();
            motionpath.addListener(new MotionPathListener() {
                @Override
                public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                    if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
                        motionevent.pause();
                        motionpath = new MotionPath();

                        Vector3f grabberLoc = ContainingClient.seaShipCranes.get(ID).getGrabber().getLocalTranslation();
                        motionpath.addWayPoint(grabberLoc);

                        Vector3f loc2 = new Vector3f(grabberLoc.x + Container.width * location.z, grabberLoc.y, grabberLoc.z);
                        if (!loc2.equals(grabberLoc)) {
                            motionpath.addWayPoint(loc2);
                        } else {
                            motionpath.addWayPoint(new Vector3f(loc2.x + 0.00001f, loc2.y, loc2.z));
                        }

                        Vector3f loc3 = new Vector3f(grabberLoc.x + Container.width * location.z, grabberLoc.y + Container.height * (-3 + location.y), grabberLoc.z);
                        if (!loc3.equals(grabberLoc)) {
                            motionpath.addWayPoint(new Vector3f(loc3.x + 0.00001f, loc3.y, loc3.z));
                        } else {
                            motionpath.addWayPoint(loc3);
                        }

                        motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
                        motionpath.setCurveTension(0f);

                        motionevent = new MotionEvent(ContainingClient.seaShipCranes.get(ID).getGrabber(), motionpath);
                        motionevent.setDirectionType(MotionEvent.Direction.None);
                        if (dayLength < 10f) {
                            motionevent.setInitialDuration(1f);
                        } else if (dayLength >= 10f && dayLength < 30f) {
                            motionevent.setInitialDuration(10f);
                        } else if (dayLength >= 30f) {
                            motionevent.setInitialDuration(30f);
                        }
                        motionevent.setSpeed(1f);
                        motionevent.play();
                        motionpath.addListener(new MotionPathListener() {
                            @Override
                            public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                                if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 2) {
                                    ContainingClient.seaShipCranes.get(ID).connectContainer(ContainingClient.seaShips.get(0).getContainerAt(ContainerID));
                                    motionevent.pause();
                                    motionpath = new MotionPath();
                                    Vector3f grabberLoc = ContainingClient.seaShipCranes.get(ID).getGrabber().getLocalTranslation();
                                    motionpath.addWayPoint(grabberLoc);
                                    motionpath.addWayPoint(new Vector3f(grabberLoc.x, 10, grabberLoc.z));
                                    motionpath.addWayPoint(new Vector3f(grabberLoc.x + 65, 10, grabberLoc.z));
                                    motionpath.addWayPoint(new Vector3f(grabberLoc.x + 65, -10, grabberLoc.z));

                                    motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
                                    motionpath.setCurveTension(0f);
                                    motionevent = new MotionEvent(ContainingClient.seaShipCranes.get(ID).getGrabber(), motionpath);
                                    motionevent.setDirectionType(MotionEvent.Direction.None);
                                    if (dayLength < 10f) {
                                        motionevent.setInitialDuration(1f);
                                    } else if (dayLength >= 10f && dayLength < 30f) {
                                        motionevent.setInitialDuration(10f);
                                    } else if (dayLength >= 30f) {
                                        motionevent.setInitialDuration(30f);
                                    }
                                    motionevent.setSpeed(1f);
                                    motionevent.play();
                                    motionpath.addListener(new MotionPathListener() {
                                        @Override
                                        public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                                            if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
                                                motionevent.pause();
                                                motionpath = new MotionPath();
                                                Vector3f grabberLoc = ContainingClient.seaShipCranes.get(ID).getGrabber().getLocalTranslation();
                                                motionpath.addWayPoint(grabberLoc);
                                                motionpath.addWayPoint(new Vector3f(grabberLoc.x, 10, grabberLoc.z));
                                                motionpath.addWayPoint(new Vector3f(-28, -2, 0));

                                                motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
                                                motionpath.setCurveTension(0f);
                                                motionevent = new MotionEvent(ContainingClient.seaShipCranes.get(ID).getGrabber(), motionpath);
                                                motionevent.setDirectionType(MotionEvent.Direction.None);
                                                if (dayLength < 10f) {
                                                    motionevent.setInitialDuration(1f);
                                                } else if (dayLength >= 10f && dayLength < 30f) {
                                                    motionevent.setInitialDuration(10f);
                                                } else if (dayLength >= 30f) {
                                                    motionevent.setInitialDuration(30f);
                                                }
                                                motionevent.setSpeed(1f);
                                                motionevent.play();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        } else {
            //start moving the whole crane to the z location of the container
            Unloading = true;
            motionpath = new MotionPath();
            Vector3f loc = this.getLocalTranslation();
            motionpath.addWayPoint(this.getLocalTranslation());
            Vector3f loc2 = new Vector3f(loc.x, loc.y, 570 + (location.z * Container.length));

            if (!loc2.equals(loc)) {
                motionpath.addWayPoint(loc2);
            } else {
                motionpath.addWayPoint(new Vector3f(loc2.x + 0.00001f, loc2.y + 0.00001f, loc2.z + 0.00001f));
            }

            motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
            motionpath.setCurveTension(0f);

            motionevent = new MotionEvent(this, motionpath);
            motionevent.setDirectionType(MotionEvent.Direction.None);
            if (dayLength < 10f) {
                motionevent.setInitialDuration(1f);
            } else if (dayLength >= 10f && dayLength < 30f) {
                motionevent.setInitialDuration(10f);
            } else if (dayLength >= 30f) {
                motionevent.setInitialDuration(30f);
            }
            motionevent.setSpeed(1f);
            motionevent.play();
            motionpath.addListener(new MotionPathListener() {
                @Override
                public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                    if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
                        //moves the hook to the x location, then down to the container
                        motionevent.pause();
                        motionpath = new MotionPath();

                        Vector3f grabberLoc = ContainingClient.bargeCranes.get(ID).getGrabber().getLocalTranslation();
                        motionpath.addWayPoint(grabberLoc);

                        Vector3f loc2 = new Vector3f(grabberLoc.x , grabberLoc.y, grabberLoc.z +(Container.width * location.z));
                        if (!loc2.equals(grabberLoc)) {
                            motionpath.addWayPoint(loc2);
                        } else {
                            motionpath.addWayPoint(new Vector3f(loc2.x + 0.00001f, loc2.y, loc2.z));
                        }

                        Vector3f loc3 = new Vector3f(grabberLoc.x + Container.width * location.z, grabberLoc.y + Container.height * (-3 + location.y), grabberLoc.z);
                        if (!loc3.equals(grabberLoc)) {
                            motionpath.addWayPoint(new Vector3f(loc3.x + 0.00001f, loc3.y, loc3.z));
                        } else {
                            motionpath.addWayPoint(loc3);
                        }

                        motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
                        motionpath.setCurveTension(0f);

                        motionevent = new MotionEvent(ContainingClient.bargeCranes.get(ID).getGrabber(), motionpath);
                        motionevent.setDirectionType(MotionEvent.Direction.None);
                        if (dayLength < 10f) {
                            motionevent.setInitialDuration(1f);
                        } else if (dayLength >= 10f && dayLength < 30f) {
                            motionevent.setInitialDuration(10f);
                        } else if (dayLength >= 30f) {
                            motionevent.setInitialDuration(30f);
                        }
                        motionevent.setSpeed(1f);
                        motionevent.play();
                        motionpath.addListener(new MotionPathListener() {
                            @Override
                            public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                                if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 2) {
                                    ContainingClient.bargeCranes.get(ID).connectContainer(ContainingClient.barges.get(0).getContainerAt(ContainerID));
                                    motionevent.pause();
                                    motionpath = new MotionPath();
                                    Vector3f grabberLoc = ContainingClient.bargeCranes.get(ID).getGrabber().getLocalTranslation();
                                    motionpath.addWayPoint(grabberLoc);
                                    motionpath.addWayPoint(new Vector3f(grabberLoc.x, 10, grabberLoc.z));
                                    motionpath.addWayPoint(new Vector3f(grabberLoc.x + 65, 10, grabberLoc.z));
                                    motionpath.addWayPoint(new Vector3f(grabberLoc.x + 65, -10, grabberLoc.z));

                                    motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
                                    motionpath.setCurveTension(0f);
                                    motionevent = new MotionEvent(ContainingClient.bargeCranes.get(ID).getGrabber(), motionpath);
                                    motionevent.setDirectionType(MotionEvent.Direction.None);
                                    if (dayLength < 10f) {
                                        motionevent.setInitialDuration(1f);
                                    } else if (dayLength >= 10f && dayLength < 30f) {
                                        motionevent.setInitialDuration(10f);
                                    } else if (dayLength >= 30f) {
                                        motionevent.setInitialDuration(30f);
                                    }
                                    motionevent.setSpeed(1f);
                                    motionevent.play();
                                    motionpath.addListener(new MotionPathListener() {
                                        @Override
                                        public void onWayPointReach(MotionEvent motionControl, int wayPointIndex) {
                                            if (motionControl.getPath().getNbWayPoints() == wayPointIndex + 1) {
                                                motionevent.pause();
                                                motionpath = new MotionPath();
                                                Vector3f grabberLoc = ContainingClient.bargeCranes.get(ID).getGrabber().getLocalTranslation();
                                                motionpath.addWayPoint(grabberLoc);
                                                motionpath.addWayPoint(new Vector3f(grabberLoc.x, 10, grabberLoc.z));
                                                motionpath.addWayPoint(new Vector3f(-28, -2, 0));

                                                motionpath.enableDebugShape(ContainingClient.getMyAssetManager(), ContainingClient.getMyRootNode());
                                                motionpath.setCurveTension(0f);
                                                motionevent = new MotionEvent(ContainingClient.bargeCranes.get(ID).getGrabber(), motionpath);
                                                motionevent.setDirectionType(MotionEvent.Direction.None);
                                                if (dayLength < 10f) {
                                                    motionevent.setInitialDuration(1f);
                                                } else if (dayLength >= 10f && dayLength < 30f) {
                                                    motionevent.setInitialDuration(10f);
                                                } else if (dayLength >= 30f) {
                                                    motionevent.setInitialDuration(30f);
                                                }
                                                motionevent.setSpeed(1f);
                                                motionevent.play();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
